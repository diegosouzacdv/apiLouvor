package com.pv.louvor.services.exceptions;

public class ObjectFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectFoundException(String msg) {
		super(msg);
	}
	
	public ObjectFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
