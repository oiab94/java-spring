package com.oiab.database.dao.implementation;

import com.oiab.database.TestDataUtil;
import com.oiab.database.dao.AuthorDao;
import com.oiab.database.domain.Author;
import com.oiab.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
		Book book = TestDataUtil.createTestBookA();
		book.setAuthorId(author.getId());
		underTest.createBook(book);
		Optional<Book> result = underTest.findOneBook(book.getIsbn());

		// Test
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}

	@Test
	public void testThatCanBeCreatedAndFindManyBooks() {
		// Creamos nuestro autor
		Author author = TestDataUtil.createTestAuthorA();
		authorDao.createAuthor(author);

		// Creamos nuestros libros
		Book bookA = TestDataUtil.createTestBookA();
		bookA.setAuthorId(author.getId());
		underTest.createBook(bookA);

		Book bookB = TestDataUtil.createTestBookB();
		bookB.setAuthorId(author.getId());
		underTest.createBook(bookB);

		Book bookC = TestDataUtil.createTestBookC();
		bookC.setAuthorId(author.getId());
		underTest.createBook(bookC);

		// Test
		List<Book> result = underTest.findBooks();
		assertThat(result)
			.hasSize(3)
			.containsExactly(bookC, bookB, bookA);
	}
}
