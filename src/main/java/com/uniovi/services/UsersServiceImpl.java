package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.dto.ChangePasswordForm;
import com.uniovi.entities.Role;
import com.uniovi.entities.User;
import com.uniovi.excepcions.UsernameOrIdNotFound;
import com.uniovi.repositories.UserRepository;

@Service
public class UsersServiceImpl implements UserService{

	

	
	@Autowired UserRepository repository;
	@Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired MapService mapService;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}

	@Override
	public User createUser(User user) throws Exception {
		String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		user = repository.save(user);
		return null;
	}

	@Override
	public User getUserById(Long id) throws UsernameOrIdNotFound {
		return repository.findById(id).orElseThrow( () -> new UsernameOrIdNotFound("El Id del usuario no existe"));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		try {
			User toUser = getUserById(fromUser.getId());
			mapUser(fromUser, toUser);	
			return repository.save(toUser);
		} catch (Exception e) {
			throw new Exception("Username o email ya utilizado");
		}
	}
	
	/**
	 * Map everythin but the password.
	 * @param from
	 * @param to
	 */
	protected void mapUser(User from,User to) {
		to.setUsername(from.getUsername());
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
		to.setRoles(from.getRoles());
	}
	
	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public void deleteUser(Long id) throws UsernameOrIdNotFound {
		User user = getUserById(id);
		mapService.deleteMaps(user);
		repository.delete(user);
	}
	
	@Override
	public User changePassword(ChangePasswordForm form) throws Exception{
		User user = repository
				.findById( form.getId() )
				.orElseThrow(() -> new Exception("UsernotFound in ChangePassword -"+this.getClass().getName()));
		
		if(form.getCurrentPassword().isEmpty()) {
			throw new Exception(getCurrentPasswordBlank());
		}
		if(form.getNewPassword().isEmpty()) {
			throw new Exception(getNewPasswordBlank());
		}
		if(form.getConfirmPassword().isEmpty()) {
			throw new Exception(getConfirmPasswordBlank());
		}
		if(!isLoggedUserADMIN() && !bCryptPasswordEncoder.matches(form.getCurrentPassword(), user.getPassword())) {
			throw new Exception(getCurrentPasswordIncorrect());
		}
		
		if ( bCryptPasswordEncoder.matches( form.getNewPassword(), user.getPassword()) ) {
			throw new Exception(getNewPasswordDiferentCurrent());
		}
		
		if( !form.getNewPassword().equals(form.getConfirmPassword())) {
			
			throw new Exception(getNewPassworAndConfirmNotMatch());
		}
		
		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		user.setPassword(encodePassword);
		return repository.save(user);
	}
	
	

	public boolean isLoggedUserADMIN(){
		return loggedUserHasRole("ROLE_ADMIN");
	}

	public boolean loggedUserHasRole(String role) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		Object roles = null; 
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		
			roles = loggedUser.getAuthorities().stream()
					.filter(x -> role.equals(x.getAuthority() ))      
					.findFirst().orElse(null); //loggedUser = null;
		}
		return roles != null ?true :false;
	}
		
	/*
	 * Obtiene el usuario autenticado
	 */
	public User getLoggedUser() throws Exception {
		//Obtener el usuario logeado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		UserDetails loggedUser = null;

		//Verificar que ese objeto traido de sesion es el usuario
		if (principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
		}
		
		User myUser = repository
				.findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Problemas obteniendo el usuario en sesión."));
		
		return myUser;
	}

	@Override
	public User getUserByUsername(String username) throws Exception {
		return repository.findByUsername(username).orElseThrow( () -> new UsernameOrIdNotFound("El username del usuario no existe"));
	}

	@Override
	public void giveUserRole(User user) {
		Set<Role> rol = new HashSet<Role>();
		rol.add(new Role(2L, "USER","ROLE_USER"));
		user.setRoles(rol);
	}
	
	
	//Internacionalizacion
	@Autowired
	private MessageSource mensajes;
	
	public String getCurrentPasswordIncorrect() {
		return mensajes.getMessage("password.currentIncorrect", null, LocaleContextHolder.getLocale());
	}
	
	public String getNewPasswordDiferentCurrent() {
		return mensajes.getMessage("password.newPasswordDifCurrent", null, LocaleContextHolder.getLocale());
	}
	
	public String getNewPassworAndConfirmNotMatch() {
		return mensajes.getMessage("password.newPasswordAndConfirmnNotMatch", null, LocaleContextHolder.getLocale());
	}
	private String getNewPasswordBlank() {
		return mensajes.getMessage("password.newPasswordBlank", null, LocaleContextHolder.getLocale());
	}
	private String getCurrentPasswordBlank() {
		return mensajes.getMessage("password.currentBlank", null, LocaleContextHolder.getLocale());
	}
	private String getConfirmPasswordBlank() {
		return mensajes.getMessage("password.confirmPasswordBlank", null, LocaleContextHolder.getLocale());
	}
}
