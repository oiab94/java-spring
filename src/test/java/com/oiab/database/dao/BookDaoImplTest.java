package com.oiab.database.dao;

import com.oiab.database.dao.implementation.BookDaoImpl;
import com.oiab.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
		Book book = Book
			.builder()
			.id(1L)
			.isbn("1234567890")
			.title("Book Title")
			.authorId(1)
			.build();


		// Esta implementacion es necesaria para crear un libro
		underTest.createBook(book);

		verify(jdbcTemplate).update(
			eq("INSERT INTO books (id, isbn, title, author_id) VALUES (?, ?, ?, ?)"),
			eq(1L), eq("1234567890"), eq("Book Title"), eq(1)
		);
	}
}
