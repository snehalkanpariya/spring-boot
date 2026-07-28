package com.gvp.unit4.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class BookRequest {

	@NotBlank(message = "title is required")
	private String title;

	@NotBlank(message = "author is required")
	private String author;

	@NotNull(message = "categoryId is required")
	private Long categoryId;

	@NotNull(message = "price is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "price must be greater than 0")
	private BigDecimal price;

	@NotNull(message = "stock is required")
	@Min(value = 0, message = "stock cannot be negative")
	private Integer stock;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
