package com.tanishk.tms.exception;

public class LoadBookedException extends RuntimeException {
	
	public LoadBookedException(String message) {
        super(message);
    }

    public LoadBookedException(String message, Throwable cause) {
        super(message, cause);
    }
}
