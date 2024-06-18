package com.oiab.database.dao;

import com.oiab.database.dao.implementation.AuthorDaoImpl;
import com.oiab.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {
	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private AuthorDaoImpl underTest;

	@Test
	public void testCreateAuthor() {
		Author author = Author.builder()
			.id(1L)
			.name("John Doe")
			.age(30)
			.build();

		// Esta implementacion es necesaria para crear un autor
		underTest.createAuthor(author);

		verify(jdbcTemplate).update(
			eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
			eq(1L),
			eq("John Doe"),
			eq(30)
		);
	}

}
