package com.gvp.unit4.controller;

import com.gvp.unit4.dto.ApiResponse;
import com.gvp.unit4.dto.CategoryRequest;
import com.gvp.unit4.dto.CategoryResponse;
import com.gvp.unit4.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Read is public; create requires ROLE_ADMIN (enforced in SecurityConfig.apiFilterChain). */
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "Book categories - GET is public, POST requires ROLE_ADMIN")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Operation(summary = "List all categories (public)")
	@GetMapping
	public ApiResponse<List<CategoryResponse>> getAllCategories() {
		return ApiResponse.ok("Categories fetched successfully", categoryService.findAll());
	}

	@Operation(summary = "Get a single category by its id (public)")
	@GetMapping("/{id}")
	public ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
		return ApiResponse.ok("Category fetched successfully", categoryService.findById(id));
	}

	@Operation(summary = "Create a new category - requires ROLE_ADMIN")
	@PostMapping
	public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@Valid @RequestBody CategoryRequest request) {
		CategoryResponse created = categoryService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Category created successfully", created));
	}
}
