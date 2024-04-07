package com.backend.exception;

@SuppressWarnings("serial")
public class SizeNotFound extends RuntimeException {
	public SizeNotFound(String id) {
		super("Not found size with id: " + id);
	}
}
