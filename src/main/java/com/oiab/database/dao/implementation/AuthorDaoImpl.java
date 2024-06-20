package com.oiab.database.dao.implementation;

import com.oiab.database.dao.AuthorDao;
import com.oiab.database.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
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

	@Override
	public Optional<Author> findOneAuthor(Long authorId) {
		List<Author> result = jdbcTemplate.query(
			"SELECT * FROM authors WHERE id = ? LIMIT 1",
			new AuthorRowMapper(),
			authorId
		);

		return result.stream().findFirst();
	}

	@Override
	public List<Author> findAuthors() {
		List<Author> result = jdbcTemplate.query(
			"SELECT * FROM authors ORDER BY 1 DESC;",
			new AuthorRowMapper()
		);

		return result;
	}

	@Override
	public void updateAuthor(long id, Author author) {
		jdbcTemplate.update(
			"UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
			author.getId(), author.getName(), author.getAge(), author.getId()
		);
	}


	// Rowmapper permite mapear los resultados de una consulta a un objeto
	public static class AuthorRowMapper implements RowMapper<Author> {
		@Override
		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Author
				.builder()
				.id(rs.getLong("id"))
				.name(rs.getString("name"))
				.age(rs.getInt("age"))
				.build();
		}
	}
}
