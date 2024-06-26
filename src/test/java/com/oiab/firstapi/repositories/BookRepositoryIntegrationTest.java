package com.oiab.firstapi.repositories;

import com.oiab.firstapi.dao.Author;
import com.oiab.firstapi.dao.Book;
import com.oiab.firstapi.utils.TestDataUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
	private final BookRepository underTest;

	@Autowired
	public BookRepositoryIntegrationTest(final BookRepository underTest) {
		this.underTest = underTest;
	}


	@Test
	@DisplayName( value = "Create a book and Find by id")
	public void testThatABookCanBeCreatedAndRecalledById() {
		// Creamos un libro y lo guardamos en la base de datos
		Author author = TestDataUtil.createTestAuthorA();
		Book book = TestDataUtil.createTestBookA(author);

		underTest.save(book);

		// Buscamos el libro
		Optional<Book> result = underTest.findById(book.getIsbn());

		// Test
		assertThat(result)
			.isNotEmpty()
			.contains(book);
	}

	@Test
	@DisplayName( value = "Create Many books and Find all the books")
	public void testThatCanBeCreatedAndFindManyBooks() {
		// Creamos varios libros y lo guardamos en la base de datos
		Author author = TestDataUtil.createTestAuthorA();
		Book bookA = TestDataUtil.createTestBookA(author);
		Book bookB = TestDataUtil.createTestBookB(author);
		Book bookC = TestDataUtil.createTestBookC(author);


		underTest.save(bookA);
		underTest.save(bookB);
		underTest.save(bookC);

		// Buscamos los libros
		Iterable<Book> result = underTest.findAll();

		// Test
		assertThat(result)
			.isNotEmpty()
			.hasSize(3)
			.contains(bookA, bookB, bookC);
	}

	@Test
	@DisplayName( value = "Create and Update the name of the book")
	public void testThatCanBeCreateAndUpdateABook() {
		// Creamos un libro y lo guardamos en la base de datos
		Author author = TestDataUtil.createTestAuthorA();
		Book book = TestDataUtil.createTestBookA(author);

		underTest.save(book);

		//Actualizamos el nombre del libro
		book.setTitle("Updated book");
		underTest.save(book);

		// Buscamos el libro
		Optional<Book> result = underTest.findById(book.getIsbn());

		// Test
		assertThat(result.get())
			.isEqualTo(book);
	}

	@Test
	@DisplayName( value = "Create and delete a book")
	public void testThatCanBeCreateAndDeleteABook() {
		// Creamos un libro y lo guardamos en la base de datos
		Author author = TestDataUtil.createTestAuthorA();
		Book book = TestDataUtil.createTestBookA(author);

		underTest.save(book);

		// Borramos el libro
		underTest.deleteById(book.getIsbn());

		// Test
		Optional<Book> result = underTest.findById(book.getIsbn());
		assertThat(result)
			.isEmpty();
	}
}
