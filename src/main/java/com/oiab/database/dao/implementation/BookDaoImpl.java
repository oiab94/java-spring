package com.oiab.database.dao.implementation;

import com.oiab.database.dao.BookDao;
import com.oiab.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {
	private final JdbcTemplate jdbcTemplate;

	public BookDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createBook(Book book) {
		jdbcTemplate.update(
			"INSERT INTO books (id, isbn, title, author_id) VALUES (?, ?, ?, ?)",
			book.getId(),
			book.getIsbn(),
			book.getTitle(),
			book.getAuthorId() );
	}
}
