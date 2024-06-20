package com.oiab.database.dao.implementation;

import com.oiab.database.TestDataUtil;
import com.oiab.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
		Author author = TestDataUtil.createTestAuthorA();

		// Esta implementacion es necesaria para crear un autor
		underTest.createAuthor(author);

		verify(jdbcTemplate).update(
			eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
			eq(1L),
			eq("John Doe"),
			eq(30)
		);
	}

	@Test
	public void testThatFindOneAuthor() {

		// Implementar la prueba para verificar que se esta llamando al metodo correcto
		underTest.findOneAuthor(1L);

		verify(jdbcTemplate).query(
			eq("SELECT * FROM authors WHERE id = ? LIMIT 1"),
			ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
			eq(1L) );

	}

	@Test
	public void testThatFindManyAuthors() {
		underTest.findAuthors();

		verify(jdbcTemplate).query(
			eq("SELECT * FROM authors ORDER BY 1 DESC;"),
			ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
		);
	}

	@Test
	public void testThatUpdateAuthor() {
		Author author = TestDataUtil.createTestAuthorA();

		underTest.updateAuthor(1L, author);

		verify(jdbcTemplate).update(
			eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
			eq(1L), eq("John Doe"), eq(30), eq(1L)
		);
	}

	@Test
	public void testThatDeleteAuthor() {
		underTest.deleteAuthor(1L);

		verify(jdbcTemplate).update(
			eq("DELETE FROM authors WHERE id = ?"),
			eq(1L)
		);
	}
}
