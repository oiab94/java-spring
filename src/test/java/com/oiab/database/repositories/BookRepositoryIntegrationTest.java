package com.oiab.database.repositories;

import com.oiab.database.TestDataUtil;
import com.oiab.database.domain.Author;
import com.oiab.database.domain.Book;
import com.oiab.database.respositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
	private BookRepository underTest;

	@Autowired
	public BookRepositoryIntegrationTest(BookRepository underTest) {
		this.underTest = underTest;
	}

	@Test
	public void testThatBookCanBeCreatedAndRecalled() {
		Author author = TestDataUtil.createTestAuthorA();

		// Creamos nuestro libro
		Book book = TestDataUtil.createTestBookA(author);

		underTest.save(book);
		Optional<Book> result = underTest.findById(book.getId());

		// Test
		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);
	}

	@Test
	public void testThatCanBeCreatedAndFindManyBooks() {
		// Creamos nuestro autor
		Author author = TestDataUtil.createTestAuthorA();

		// Creamos nuestros libros
		Book bookA = TestDataUtil.createTestBookA(author);
		underTest.save(bookA);

		Book bookB = TestDataUtil.createTestBookB(author);
		underTest.save(bookB);

		Book bookC = TestDataUtil.createTestBookC(author);
		underTest.save(bookC);

		// Test
		Iterable<Book> result = underTest.findAll();
		assertThat(result)
			.hasSize(3)
			.containsExactly(bookA, bookB, bookC);
	}

	@Test
	public void testThatCanBeCreatedAndUpdatedABook() {
		// Creamos nuestro autor
		Author author = TestDataUtil.createTestAuthorA();

		// Creamos un libro
		Book book = TestDataUtil.createTestBookA(author);
		underTest.save(book);

		// Test
		book.setTitle("Updated Title");

		underTest.save(book);

		Optional<Book> result = underTest.findById(book.getId());

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(book);

	}

	@Test
	public void testThatCanBeCreatedAndDeletedABook() {
		// Creamos nuestro autor
		Author author = TestDataUtil.createTestAuthorA();

		// Creamos un libro
		Book book = TestDataUtil.createTestBookA(author);
		underTest.save(book);

		// Test
		underTest.deleteById(book.getId());
		Optional<Book> result = underTest.findById(book.getId());

		assertThat(result).isEmpty();
	}
}