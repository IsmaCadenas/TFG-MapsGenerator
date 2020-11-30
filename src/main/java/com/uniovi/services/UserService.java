package com.uniovi.services;


import com.uniovi.dto.ChangePasswordForm;
import com.uniovi.entities.User;
import com.uniovi.excepcions.UsernameOrIdNotFound;

public interface UserService {

	public Iterable<User> getAllUsers();
	public User createUser(User user) throws Exception;
	public User getUserById(Long id) throws Exception;
	public User updateUser(User user) throws Exception;
	public void deleteUser(Long id) throws UsernameOrIdNotFound;
	public User changePassword(ChangePasswordForm form) throws Exception;
	public User getUserByUsername(String username) throws Exception;
	public void giveUserRole(User user);
}
