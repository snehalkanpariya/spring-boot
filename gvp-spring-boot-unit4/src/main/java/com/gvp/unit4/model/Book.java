package com.gvp.unit4.model;

import java.math.BigDecimal;

public class Book {

	private Long id;
	private String title;
	private String author;
	private Long categoryId;
	private BigDecimal price;
	private int stock;

	public Book(Long id, String title, String author, Long categoryId, BigDecimal price, int stock) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.categoryId = categoryId;
		this.price = price;
		this.stock = stock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
}
