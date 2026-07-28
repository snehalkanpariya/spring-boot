package com.gvp.unit4.service;

import com.gvp.unit4.dto.CategoryRequest;
import com.gvp.unit4.dto.CategoryResponse;
import com.gvp.unit4.exception.ResourceNotFoundException;
import com.gvp.unit4.model.Category;
import com.gvp.unit4.repository.CategoryRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryResponse> findAll() {
		return categoryRepository.findAll().stream().map(this::toResponse).toList();
	}

	@Override
	public CategoryResponse findById(Long id) {
		return toResponse(getOrThrow(id));
	}

	@Override
	public CategoryResponse create(CategoryRequest request) {
		Category saved = categoryRepository.save(new Category(null, request.getName(), request.getDescription()));
		return toResponse(saved);
	}

	private Category getOrThrow(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
	}

	private CategoryResponse toResponse(Category category) {
		return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
	}
}
