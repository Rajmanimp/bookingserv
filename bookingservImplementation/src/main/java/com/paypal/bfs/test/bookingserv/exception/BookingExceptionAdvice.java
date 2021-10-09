/**
 * 
 */
package com.paypal.bfs.test.bookingserv.exception;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * @author rajmanip
 *
 */
@ControllerAdvice
public class BookingExceptionAdvice {

	@ExceptionHandler({ ValidationException.class })
	public final ResponseEntity<ApiError> handleValidationException(ValidationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return handleExceptionInternal(new ApiError(status, ex.getLocalizedMessage(), ex));
	}

	@ExceptionHandler({ BadRequestException.class })
	public final ResponseEntity<ApiError> handleBadRequestException(BadRequestException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return handleExceptionInternal(new ApiError(status, ex.getLocalizedMessage(), ex));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<ApiError> handleEntityEntityNotFoundException(EntityNotFoundException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
		return handleExceptionInternal(apiError);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiError> handleSQLException(HttpServletRequest request, Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
				"\"SQLException occurred:: URL=\"+request.getRequestURL()", ex);
		return handleExceptionInternal(apiError);
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiError> handleIOException(IOException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "IOException occurred", ex);
		return handleExceptionInternal(apiError);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ApiError> handleAllUncaughtException(Exception exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error occurred", exception,
				request);
		return handleExceptionInternal(apiError);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleDataException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;

		return handleExceptionInternal(new ApiError(status, ex.getLocalizedMessage(), ex));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> handleValidatorException(Exception exception, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
				"Few parameters are not meeting the requited criteria. Please check the API documentations.", exception,
				request);
		return handleExceptionInternal(apiError);
	}

	protected ResponseEntity<ApiError> handleExceptionInternal(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
