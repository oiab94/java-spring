package com.oiab.database.dao.implementation;

import com.oiab.database.dao.BookDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class BookDaoImpl implements BookDao {
	private final JdbcTemplate jdbcTemplate;

	public BookDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
