/**
 * 
 */
package com.paypal.bfs.test.bookingserv.exception;

/**
 * @author rajmanip
 *
 */
public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3519774211392446170L;

	public ValidationException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public ValidationException(String errorMessage) {
		super(errorMessage);
	}

}
