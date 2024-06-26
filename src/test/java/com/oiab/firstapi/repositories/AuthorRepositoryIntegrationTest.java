package com.oiab.firstapi.repositories;

import com.oiab.firstapi.dao.Author;
import com.oiab.firstapi.utils.TestDataUtil;
import jakarta.persistence.OrderBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Iterator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTest {
	private final AuthorRepository underTest;

	@Autowired
	public AuthorRepositoryIntegrationTest(final AuthorRepository underTest) {
		this.underTest = underTest;
	}


	@Test
	@DisplayName( value = "This test created and author and find by id")
	public void testThatAndAuthorCanBeCreatedAndRecalledById() {
		// Creamos y guardamos el autor en la bae de datos
		Author author = TestDataUtil.createTestAuthorA();
		underTest.save(author);

		// Failed Test
		// Optional<Author> result = underTest.findById(99L);


		// Buscamos el autor
		Optional<Author> result = underTest.findById(author.getId());

		// Test
		assertThat(result.get())
			.isEqualTo(author);
	}

	@Test
	@DisplayName( value = "This test create many author and find all the authors")
	public void testThatCanBeCreatedAndFindManyAuthors() {
		// Creamos y guardamos los autores
		Author authorA = TestDataUtil.createTestAuthorA();
		Author authorB = TestDataUtil.createTestAuthorB();
		Author authorC = TestDataUtil.createTestAuthorC();

		underTest.save(authorA);
		underTest.save(authorB);
		underTest.save(authorC);

		// Buscamos todos los autores
		Iterable<Author> result = underTest.findAllByIdAsc();

		// Test
		assertThat(result)
			.isNotEmpty()
			.hasSize(3)
			.containsExactly(authorA, authorB, authorC);
	}

	@Test
	@DisplayName( value = "This test create and author and patch the name of the author")
	public void testThatCanBeCreatedAndUpdatedAndAuthor() {
		// Creamos y guardamos un autor
		Author author = TestDataUtil.createTestAuthorA();

		underTest.save(author);

		// Reemplazamos el nombre del autor y guardamos
		author.setName("Updated Name");
		underTest.save(author);

		// Test
		Optional<Author> result = underTest.findById(author.getId());
		assertThat(result.get())
			.isEqualTo(author);
	}

	@Test
	@DisplayName( value = "This test create and author and then delete the same author")
	public void testThatCanBeCreatedAndDeleteAndAuthor() {
		// Creamos y guardamos los autores
		Author author = TestDataUtil.createTestAuthorA();

		underTest.save(author);

		// Borramos el autor creado
		underTest.deleteById(author.getId());

		// Test
		Optional<Author> result = underTest.findById(author.getId());
		assertThat(result)
			.isEmpty();
	}


	/**
	 * TEST OF CUSTOM QUERIES
	 */
	@Test
	@DisplayName(value = "This test can created authors and find the authors with age less than 30")
	public void testThatCanGetAuthorsWithAgeLessThan() {
		// Creamos y guardamos los autores
		Author authorA = TestDataUtil.createTestAuthorA();
		Author authorB = TestDataUtil.createTestAuthorB();
		Author authorC = TestDataUtil.createTestAuthorC();

		underTest.save(authorA);
		underTest.save(authorB);
		underTest.save(authorC);

		// Buscamos autores menores a 30 años
		int ageLessThan = 30;
		Iterable<Author> result = underTest.findByAgeLessThan(ageLessThan);

		// Test
		assertThat(result)
			.isNotEmpty()
			.containsExactly(authorB);
	}

	@Test
	@DisplayName(value = "This test can created authors and find the authors with age greater than 30")
	public void testThatCanGetAuthorsWithAgeGreaterThan() {
		// Creamos y guardamos los authores
		Author authorA = TestDataUtil.createTestAuthorA();
		Author authorB = TestDataUtil.createTestAuthorB();
		Author authorC = TestDataUtil.createTestAuthorC();

		underTest.save(authorA);
		underTest.save(authorB);
		underTest.save(authorC);

		// Buscamos autores mayores a 30 años
		int ageGreaterThan = 30;
		Iterable<Author> result = underTest.findByAgeGreaterThan(ageGreaterThan);

		// Test
		assertThat(result)
			.isNotEmpty()
			.containsExactly(authorC);
	}
}
