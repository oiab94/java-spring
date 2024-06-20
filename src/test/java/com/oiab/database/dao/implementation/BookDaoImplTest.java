package com.oiab.database.dao.implementation;

import com.oiab.database.TestDataUtil;
import com.oiab.database.domain.Book;
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
public class BookDaoImplTest {
	@Mock
	private JdbcTemplate jdbcTemplate;

	@InjectMocks
	private BookDaoImpl underTest;

	@Test
	public void testCreateBook(){
		Book book = TestDataUtil.createTestBookA();


		// Esta implementacion es necesaria para crear un libro
		underTest.createBook(book);

		verify(jdbcTemplate).update(
			eq("INSERT INTO books (id, isbn, title, author_id) VALUES (?, ?, ?, ?)"),
			eq(1L), eq("1234567890"), eq("Book Title"), eq(1L)
		);
	}

	@Test
	public void testThatFindOneBook() {
		// Implementar la prueba para verificar que se esta llamando al método correcto
		underTest.findOneBook("1234567890");

		verify(jdbcTemplate).query(
			eq("SELECT * FROM books WHERE isbn = ? LIMIT 1"),
			ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
			eq("1234567890"));
	}

	@Test
	public void testThatFindManyBooks() {
		underTest.findBooks();

		verify(jdbcTemplate).query(
			eq("SELECT * FROM books ORDER BY 1 DESC;"),
			ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
		);
	}

	@Test
	public void testThatUpdateBook() {
		Book book = TestDataUtil.createTestBookA();

		underTest.updateBook(1L, book);

		verify(jdbcTemplate).update(
			eq("UPDATE books SET id = ?, isbn = ?, title = ?, author_id = ? WHERE id = ?;"),
			eq(1L), eq("1234567890"), eq("Book Title"), eq(1L), eq(1L)
		);
	}

	@Test
	public void testThatDeleteABook() {
		underTest.deleteBook(1L);

		verify(jdbcTemplate).update(
			eq("DELETE FROM books WHERE id = ?"),
			eq(1L)
		);
	}
}
