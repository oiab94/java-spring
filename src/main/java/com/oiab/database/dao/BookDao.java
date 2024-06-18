package com.oiab.database.dao;

import com.oiab.database.domain.Book;

import java.util.Optional;

public interface BookDao {
	void createBook(Book book);

	Optional<Book> findOneBook(String isbn);
}
