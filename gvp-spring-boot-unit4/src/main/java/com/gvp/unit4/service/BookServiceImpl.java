package com.gvp.unit4.service;

import com.gvp.unit4.dto.BookRequest;
import com.gvp.unit4.dto.BookResponse;
import com.gvp.unit4.exception.ResourceNotFoundException;
import com.gvp.unit4.model.Book;
import com.gvp.unit4.model.Category;
import com.gvp.unit4.repository.BookRepository;
import com.gvp.unit4.repository.CategoryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.access.prepost.PreAuthorize;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	private final BookRepository bookRepository;
	private final CategoryRepository categoryRepository;

	public BookServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<BookResponse> findAll() {
		return bookRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Override
	public BookResponse findById(Long id) {
		return toResponse(getOrThrow(id));
	}

	@Override
@PreAuthorize("hasRole('ADMIN')")
	public BookResponse create(BookRequest request) {
		requireCategoryExists(request.getCategoryId());
		Book book = new Book(null, request.getTitle(), request.getAuthor(), request.getCategoryId(),
				request.getPrice(), request.getStock());
		Book saved = bookRepository.save(book);
		log.info("Created book '{}' with id {}", saved.getTitle(), saved.getId());
		return toResponse(saved);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public BookResponse update(Long id, BookRequest request) {
		getOrThrow(id);
		requireCategoryExists(request.getCategoryId());
		Book book = new Book(id, request.getTitle(), request.getAuthor(), request.getCategoryId(),
				request.getPrice(), request.getStock());
		Book saved = bookRepository.save(book);
		log.info("Updated book with id {}", saved.getId());
		return toResponse(saved);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(Long id) {
		getOrThrow(id);
		bookRepository.deleteById(id);
		log.info("Deleted book with id {}", id);
	}

	private Book getOrThrow(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
	}

	private void requireCategoryExists(Long categoryId) {
		if (!categoryRepository.existsById(categoryId)) {
			throw new ResourceNotFoundException("Category not found with id: " + categoryId);
		}
	}

	private BookResponse toResponse(Book book) {
		String categoryName = categoryRepository.findById(book.getCategoryId()).map(Category::getName).orElse("Unknown");
		return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), categoryName, book.getPrice(), book.getStock());
	}
}
