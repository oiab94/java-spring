package com.oiab.database.dao.implementation;

import com.oiab.database.TestDataUtil;
import com.oiab.database.dao.AuthorDao;
import com.oiab.database.domain.Author;
import com.oiab.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTest {
	private AuthorDao authorDao;
	private BookDaoImpl underTest;

	@Autowired
	public BookDaoImplIntegrationTest(BookDaoImpl underTest, AuthorDao authorDao) {
		this.underTest = underTest;
		this.authorDao = authorDao;
	}

	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		Author author = TestDataUtil.createTestAuthorA();

		// Creamos nuestro autor
		authorDao.createAuthor(author);

		// Creamos nuestro libro
		Book book = TestDataUtil.createTestBook();
		book.setAuthorId(author.getId());
		underTest.createBook(book);
		Optional<Book> result = underTest.findOneBook(book.getIsbn());

		// Test
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}
}
