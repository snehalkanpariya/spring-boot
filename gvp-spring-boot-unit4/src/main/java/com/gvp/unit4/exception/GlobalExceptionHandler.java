package com.gvp.unit4.exception;

import com.gvp.unit4.dto.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.authentication.BadCredentialsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
		log.warn("Resource not found: {}", ex.getMessage());
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
		log.warn("Bad request: {}", ex.getMessage());
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), request.getRequestURI());
		return ResponseEntity.badRequest().body(error);
	}
	@ExceptionHandler(DuplicateResourceException.class)
public ResponseEntity<ApiError> handleDuplicate(DuplicateResourceException ex, HttpServletRequest request) {
    log.warn("Duplicate resource: {}", ex.getMessage());
    ApiError error = new ApiError(HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage(), request.getRequestURI());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
}
	@ExceptionHandler(BadCredentialsException.class)
public ResponseEntity<ApiError> handleBadCredentials(BadCredentialsException ex, HttpServletRequest request) {
    log.warn("Login failed for {}: {}", request.getRequestURI(), ex.getMessage());
    ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
            "Invalid username or password", request.getRequestURI());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
		Map<String, String> fieldErrors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(fe -> fieldErrors.put(fe.getField(), fe.getDefaultMessage()));
		log.warn("Validation failed for {}: {}", request.getRequestURI(), fieldErrors);
		ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Validation Failed",
				"One or more fields are invalid", request.getRequestURI(), fieldErrors);
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest request) {
		log.error("Unexpected error handling {}", request.getRequestURI(), ex);
		ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
				"Something went wrong. Please try again later.", request.getRequestURI());
		return ResponseEntity.internalServerError().body(error);
	}
}
