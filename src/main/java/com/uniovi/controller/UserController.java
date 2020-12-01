package com.uniovi.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uniovi.dto.ChangePasswordForm;
import com.uniovi.entities.Role;
import com.uniovi.entities.User;
import com.uniovi.excepcions.CustomeFieldValidationException;
import com.uniovi.excepcions.UsernameOrIdNotFound;
import com.uniovi.repositories.RoleRepository;
import com.uniovi.services.UserService;
import com.uniovi.validators.SignUpFormValidator;

@Controller
public class UserController {

	@Autowired UserService userService;
	
	@Autowired RoleRepository roleRepository;
	@Autowired SignUpFormValidator signUpFormValidator;
	
	@GetMapping({"/login"})
	public String index() {
		return "login";
	}
	
	
	@GetMapping("/signup")
	public String signUp(Model model) {
		Role userRole = roleRepository.findByName("USER");
		List<Role> roles = Arrays.asList(userRole);

		model.addAttribute("signup",true);
		model.addAttribute("userForm", new User());
		model.addAttribute("roles",roles);
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signupAction(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		Role userRole = roleRepository.findByName("USER");
		List<Role> roles = Arrays.asList(userRole);
		model.addAttribute("userForm", user);
		model.addAttribute("roles",roles);
		model.addAttribute("signup",true);

		signUpFormValidator.validate(user, result);
		if(result.hasErrors()) {
			return "signup";
		}else {
			try {
				userService.giveUserRole(user);
				userService.createUser(user);
				model.addAttribute("created",true);
			} catch (CustomeFieldValidationException cfve) {
				//result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				return "signup";
			}catch (Exception e) {
				//model.addAttribute("formErrorMessage",e.getMessage());
				return "signup";
			}
		}
		return index();
	}
	
	
	@GetMapping("/userList")
	public String userList(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("listTab", "active");
		return "user/userList";
	}
	
	
	@GetMapping("/userForm")
	public String userForm(Model model) {
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("formTab", "active");
		return "user-form/user-view";
	}
	
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		signUpFormValidator.validate(user, result);
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
		}else {
			try {
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
				model.addAttribute("formSuccessMessage", "Usuario añadido correctamenmte");
				
			}catch (CustomeFieldValidationException cfve) {
				//result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
				
			}
			catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
			}
		}
		
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		User user = userService.getUserById(id);
		
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userForm", user);
		model.addAttribute("formTab","active");//Activa el tab del formulario.
		
		model.addAttribute("editMode",true);//Mira siguiente seccion para mas informacion
		
		model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
		
		return "user-form/user-view";
	}
	

	
	@GetMapping("/editUser")
	public String getEditMyUserForm(Model model, Principal principal) throws Exception {
		
		String userName = principal.getName();
		User user = userService.getUserByUsername(userName);
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("userForm", user);
		model.addAttribute("formTab","active");//Activa el tab del formulario.
		model.addAttribute("editUser",true);
		model.addAttribute("editMode",true);//Mira siguiente seccion para mas informacion
		
		model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
		
		return "user/editUser";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("roles",roleRepository.findAll());
			model.addAttribute("editMode","true");
			model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
			return "user-form/user-view";
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
				model.addAttribute("messageUserEdited",true);
				model.addAttribute("listSuccessMessage","Datos modificados correctamente");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
				model.addAttribute("editMode","true");
				model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
				return "user-form/user-view";
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		return "user/userList";
		
	}
	
	
	@PostMapping("/editMyUser")
	public String postEditMyUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model, Principal principal) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
			model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
				model.addAttribute("listSuccessMessage", "Datos modificados correctamente");
				model.addAttribute("messageMyUserEdited",true);
			} catch (Exception e) {
				model.addAttribute("formErrorMessage", "Email no disponible");
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("roles",roleRepository.findAll());
				model.addAttribute("editMode","true");
				model.addAttribute("passwordForm", new ChangePasswordForm(user.getId()));
				return "map/editUser";
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles",roleRepository.findAll());
		return "home/index";
	}
	
	
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/";
	}
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id")Long id) {
		try {
			userService.deleteUser(id);
		} 
		catch (UsernameOrIdNotFound e) {
			model.addAttribute("listErrorMessage",e.getMessage());
		}
		return "redirect:/userList";
	}
	
	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors, Model model) {
		try {
			//If error, just return a 400 bad request, along with the error message
	        if (errors.hasErrors()) {
	            String result = errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining(""));

	            throw new Exception(result);
	        }
			userService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("success");
	}
	
	
	
}


