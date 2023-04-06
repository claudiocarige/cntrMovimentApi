package com.claudiocarige.CntrMovimentApi.services.exception;

public class NoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSuchElementException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchElementException(String message) {
		super(message);
	}

}
