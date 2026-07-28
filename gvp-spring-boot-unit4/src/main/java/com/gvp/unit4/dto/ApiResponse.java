package com.gvp.unit4.dto;

import java.time.LocalDateTime;

/** A consistent envelope every successful endpoint returns (same pattern as gvp-spring-boot-unit2). */
public class ApiResponse<T> {

	private boolean success;
	private String message;
	private T data;
	private LocalDateTime timestamp;

	public ApiResponse(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	public static <T> ApiResponse<T> ok(String message, T data) {
		return new ApiResponse<>(true, message, data);
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
