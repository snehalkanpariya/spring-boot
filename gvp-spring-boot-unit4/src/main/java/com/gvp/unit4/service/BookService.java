package com.gvp.unit4.service;

import com.gvp.unit4.dto.BookRequest;
import com.gvp.unit4.dto.BookResponse;
import java.util.List;

public interface BookService {

	List<BookResponse> findAll();

	BookResponse findById(Long id);

	BookResponse create(BookRequest request);

	BookResponse update(Long id, BookRequest request);

	void delete(Long id);
}
