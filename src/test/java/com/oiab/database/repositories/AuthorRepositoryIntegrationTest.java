package com.oiab.database.repositories;

import com.oiab.database.TestDataUtil;
import com.oiab.database.domain.Author;
import com.oiab.database.respositories.AuthorRepository;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Limpia cada cambio que se haya realizado en cada test
public class AuthorRepositoryIntegrationTest {
	private AuthorRepository underTest;

	@Autowired
	public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
		this.underTest = underTest;
	}

	/**
	 * Primero se debe crear un autor y luego se debe buscar por id
	 */
	@Test
	public void testThatAuthorCanBeCreatedAndRecalledById() {
		Author author = TestDataUtil.createTestAuthorA();
		underTest.save(author);

		// Failed Test
		//Optional<Author> result = underTest.findOneAuthor(99L);
		Optional<Author> result = underTest.findById(author.getId());

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}

	@Test
	public void testThatCanBeCreatedAndFindManyAuthors() {
		// Creamos los autores
		Author authorA = TestDataUtil.createTestAuthorA();
		Author authorB = TestDataUtil.createTestAuthorB();
		Author authorC = TestDataUtil.createTestAuthorC();

		underTest.save(authorA);
		underTest.save(authorB);
		underTest.save(authorC);

		// Buscamos los autores
		Iterable<Author> result = underTest.findAll();

		// Test
		assertThat(result)
			.hasSize(3)
			.containsExactly(authorA, authorB, authorC);
	}

	@Test
	public void testThatCanBeCreatedAndUpdatedAuthor() {
		// Creamos un autor
		Author author = TestDataUtil.createTestAuthorA();
		underTest.save(author);

		// Creamos un nuevo autor que reemplazara el anterior
		author.setName("Updated Name");
		underTest.save(author);

		// Test
		Optional<Author> result = underTest.findById(author.getId());

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}

	@Test
	public void testThatCanBeCreatedAndDeletedAuthor() {
		// Creamos un autor
		Author author = TestDataUtil.createTestAuthorA();
		underTest.save(author);

		// Test
		underTest.deleteById(author.getId());
		Optional<Author> result = underTest.findById(author.getId());

		assertThat(result).isEmpty();
	}
}