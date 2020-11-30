package com.uniovi.validators;

import com.uniovi.dto.ChangePasswordForm;
import com.uniovi.entities.User;
import com.uniovi.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class SignUpFormValidator implements Validator {
	
	
	@Autowired UserRepository repository;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		if (user.getFirstName().length() < 1 || user.getFirstName().length() > 24) {
			errors.rejectValue("firstName", "Error.signup.firstname.length");
		}
		if (user.getLastName().length() < 1 || user.getLastName().length() > 24) {
			errors.rejectValue("lastName", "Error.signup.lastname.length");
		}
		if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
			errors.rejectValue("password", "Error.signup.password.length");
		}
		if (user.getEmail().length() < 1) {
			errors.rejectValue("email", "Error.signup.email.length");
		}
		if (user.getUsername().length() < 1 || user.getUsername().length() > 24) {
			errors.rejectValue("username", "Error.signup.username.length");
		}
		if(checkMailExists(user, errors)) {
			errors.rejectValue("email", "Error.signup.mail.exists");
		}
		if(checkUsernameExists(user, errors)) {
			errors.rejectValue("username", "Error.signup.username");
		}
		checkPasswordValid(user, errors);
	}
	
	private boolean checkUsernameExists(User user,Errors errors) {
		Optional<User> userfound = repository.findByUsername(user.getUsername());
		if (userfound.isPresent()) {
			return true;
		}
		return false;
	}
	
	private boolean checkMailExists(User user, Errors errors) {
		Optional<User> userfound = repository.findByEmail(user.getEmail());
		if (userfound.isPresent()) {
			return true;
		}
		return false;
	}
	
	private boolean checkPasswordValid(User user, Errors errors) {
		if(user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			errors.rejectValue("confirmPassword", "Error.signup.confirmpassword.obligatory");
		}
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "Error.signup.password.notequals");
		}
		return false;
	}
}