package com.lti.exception;

public class UserServiceException extends RuntimeException {

	public UserServiceException() {
		super();
	}

	public UserServiceException(String message) {
		super(message);
	}
	
}