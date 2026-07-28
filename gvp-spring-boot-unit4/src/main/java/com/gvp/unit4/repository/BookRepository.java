package com.gvp.unit4.repository;

import com.gvp.unit4.model.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

	private final Map<Long, Book> books = new ConcurrentHashMap<>();
	private final AtomicLong idSequence = new AtomicLong(1);

	public Book save(Book book) {
		if (book.getId() == null) {
			book.setId(idSequence.getAndIncrement());
		}
		books.put(book.getId(), book);
		return book;
	}

	public List<Book> findAll() {
		return List.copyOf(books.values());
	}

	public Optional<Book> findById(Long id) {
		return Optional.ofNullable(books.get(id));
	}

	public void deleteById(Long id) {
		books.remove(id);
	}

	public boolean existsById(Long id) {
		return books.containsKey(id);
	}
}
