package com.oiab.database.dao;

import com.oiab.database.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
	void createBook(Book book);

	Optional<Book> findOneBook(String isbn);

	List<Book> findBooks();

	void updateBook(long bookId, Book book);
}
