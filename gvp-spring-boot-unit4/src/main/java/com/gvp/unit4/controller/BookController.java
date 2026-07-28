package com.gvp.unit4.controller;

import com.gvp.unit4.dto.ApiResponse;
import com.gvp.unit4.dto.BookRequest;
import com.gvp.unit4.dto.BookResponse;
import com.gvp.unit4.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** GET is public; POST/PUT/DELETE require ROLE_ADMIN - enforced both at the URL level (SecurityConfig) and the method level (@PreAuthorize in BookServiceImpl). */
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "CRUD for the book catalog - GET is public, writes require ROLE_ADMIN")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@Operation(summary = "List all books (public)")
	@GetMapping
	public ApiResponse<List<BookResponse>> getAllBooks() {
		return ApiResponse.ok("Books fetched successfully", bookService.findAll());
	}

	@Operation(summary = "Get a single book by its id (public)")
	@GetMapping("/{id}")
	public ApiResponse<BookResponse> getBookById(@PathVariable Long id) {
		return ApiResponse.ok("Book fetched successfully", bookService.findById(id));
	}

	@Operation(summary = "Create a new book - requires ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<ApiResponse<BookResponse>> createBook(@Valid @RequestBody BookRequest request) {
		BookResponse created = bookService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Book created successfully", created));
	}

	@Operation(summary = "Update an existing book - requires ROLE_ADMIN")
	@PutMapping("/{id}")
	public ApiResponse<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest request) {
		return ApiResponse.ok("Book updated successfully", bookService.update(id, request));
	}

	@Operation(summary = "Delete a book - requires ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
