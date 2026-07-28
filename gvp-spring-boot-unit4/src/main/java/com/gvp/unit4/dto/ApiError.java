package com.gvp.unit4.dto;

import java.time.LocalDateTime;
import java.util.Map;

/** A consistent error envelope every failed endpoint returns. */
public class ApiError {

	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	private Map<String, String> fieldErrors;

	public ApiError(int status, String error, String message, String path, Map<String, String> fieldErrors) {
		this.timestamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		this.fieldErrors = fieldErrors;
	}

	public ApiError(int status, String error, String message, String path) {
		this(status, error, message, path, null);
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}
}

