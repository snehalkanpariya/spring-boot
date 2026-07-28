package com.gvp.unit4.service;

import com.gvp.unit4.dto.CategoryRequest;
import com.gvp.unit4.dto.CategoryResponse;
import java.util.List;

public interface CategoryService {

	List<CategoryResponse> findAll();

	CategoryResponse findById(Long id);

	CategoryResponse create(CategoryRequest request);
}
