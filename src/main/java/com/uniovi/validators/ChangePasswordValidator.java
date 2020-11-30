package com.uniovi.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uniovi.dto.ChangePasswordForm;
import com.uniovi.entities.User;

@Component
public class ChangePasswordValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ChangePasswordForm form = (ChangePasswordForm)target;
		
		
		
		if( !form.getNewPassword().equals(form.getConfirmPassword())) {
			errors.rejectValue("newPassword", "Error.signup.firstname.length");
		}
	}

}
