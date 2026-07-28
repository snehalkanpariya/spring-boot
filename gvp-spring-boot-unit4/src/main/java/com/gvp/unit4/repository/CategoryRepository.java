package com.gvp.unit4.repository;

import com.gvp.unit4.model.Category;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

	private final Map<Long, Category> categories = new ConcurrentHashMap<>();
	private final AtomicLong idSequence = new AtomicLong(1);

	public Category save(Category category) {
		if (category.getId() == null) {
			category.setId(idSequence.getAndIncrement());
		}
		categories.put(category.getId(), category);
		return category;
	}

	public List<Category> findAll() {
		return List.copyOf(categories.values());
	}

	public Optional<Category> findById(Long id) {
		return Optional.ofNullable(categories.get(id));
	}

	public boolean existsById(Long id) {
		return categories.containsKey(id);
	}
}
