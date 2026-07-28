package com.gvp.unit4.dto;

import java.math.BigDecimal;

public class BookResponse {

	private Long id;
	private String title;
	private String author;
	private String categoryName;
	private BigDecimal price;
	private int stock;

	public BookResponse(Long id, String title, String author, String categoryName, BigDecimal price, int stock) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.categoryName = categoryName;
		this.price = price;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
}
