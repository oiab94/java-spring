package com.oiab.database.dao.implementation;

import com.oiab.database.dao.BookDao;
import com.oiab.database.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
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

	@Override
	public Optional<Book> findOneBook(String isbn) {
		List<Book> result = jdbcTemplate.query(
			"SELECT * FROM books WHERE isbn = ? LIMIT 1",
			new BookRowMapper(),
			isbn);

		return result.stream().findFirst();
	}

	@Override
	public List<Book> findBooks() {
		List<Book> result = jdbcTemplate.query(
	"SELECT * FROM books ORDER BY 1 DESC;",
			new BookRowMapper()
		);

		return result;
	}

	public static class BookRowMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Book
				.builder()
				.id(rs.getLong("id"))
				.isbn(rs.getString("isbn"))
				.title(rs.getString("title"))
				.authorId(rs.getLong("author_id"))
				.build();
		}
	}
}
