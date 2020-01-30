package com.pv.louvor.services.exceptions;

public class NoSuchFileException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NoSuchFileException(String msg) {
		super(msg);
	}
	
	public NoSuchFileException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
