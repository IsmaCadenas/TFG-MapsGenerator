package com.uniovi.excepcions;

public class CustomeFieldValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -293622941934980188L;

	private String fieldName;
	
	public CustomeFieldValidationException(String message, String fieldName) {
		super(message);
		this.fieldName =fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}
	

}
