package com.paypal.bfs.test.bookingserv.exception;

public class EntityNotFoundException extends RuntimeException {
	public EntityNotFoundException(Class clazz, String searchParam) {
		super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), searchParam));
	}

	private static String generateMessage(String entity, String searchParam) {
		return entity + " was not found for " + searchParam;
	}
}
