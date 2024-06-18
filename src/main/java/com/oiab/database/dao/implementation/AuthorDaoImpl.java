package com.oiab.database.dao.implementation;

import com.oiab.database.dao.AuthorDao;
import com.oiab.database.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;

public class AuthorDaoImpl implements AuthorDao {
	private final JdbcTemplate jdbcTemplate;

	public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void createAuthor(Author author) {
		jdbcTemplate.update(
			"INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
			author.getId(),
			author.getName(),
			author.getAge()
		);
	}
}
