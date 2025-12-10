package com.tanishk.tms.exception;

public class InsufficientCapacityException extends RuntimeException {
	public InsufficientCapacityException(String message) {
        super(message);
	}
}
