package com.adil.Exception;

public class UserDoesNotExitException extends RuntimeException {

	String message;

	public UserDoesNotExitException() {
	}

	public UserDoesNotExitException(String message) {
		super(message);
	}

}
