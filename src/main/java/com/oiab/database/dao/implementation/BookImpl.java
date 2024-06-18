package com.oiab.database.dao.implementation;

import com.oiab.database.dao.Book;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookImpl implements Book {
	private final JdbcTemplate jdbcTemplate;

	public BookImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
