package com.exdev.cc.bso;

public class BSOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1322216201794636263L;

	public BSOException(String message) {
		super(message);
	}

	public BSOException(String message, Throwable cause) {
		super(message, cause);
	}

	public BSOException(Throwable cause) {
		super(cause);
	}

}
