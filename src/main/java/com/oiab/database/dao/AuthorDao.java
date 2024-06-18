package com.oiab.database.dao;

import com.oiab.database.domain.Author;

import java.util.Optional;

public interface AuthorDao {
	void createAuthor(Author author);

	Optional<Author> findOneAuthor(Long id);
}
