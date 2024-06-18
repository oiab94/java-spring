package com.oiab.database.dao.implementation;

import com.oiab.database.dao.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorImpl implements Author {
	private final JdbcTemplate jdbcTemplate;

	public AuthorImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
