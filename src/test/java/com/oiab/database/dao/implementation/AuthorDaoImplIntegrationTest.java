package com.oiab.database.dao.implementation;

import com.oiab.database.TestDataUtil;
import com.oiab.database.domain.Author;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Limpia cada cambio que se haya realizado en cada test
public class AuthorDaoImplIntegrationTest {
	private AuthorDaoImpl underTest;

	@Autowired
	public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest) {
		this.underTest = underTest;
	}

	/**
	 * Primero se debe crear un autor y luego se debe buscar por id
	 */
	@Test
	public void testThatAuthorCanBeCreatedAndRecalledById() {
		Author author = TestDataUtil.createTestAuthorA();
		underTest.createAuthor(author);

		// Failed Test
		//Optional<Author> result = underTest.findOneAuthor(99L);
		Optional<Author> result = underTest.findOneAuthor(author.getId());

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}

	@Test
	public void testThatCanBeCreatedAndFindManyAuthors() {
		// Creamos los autores
		Author authorA = TestDataUtil.createTestAuthorA();
		Author authorB = TestDataUtil.createTestAuthorB();
		Author authorC = TestDataUtil.createTestAuthorC();

		underTest.createAuthor(authorA);
		underTest.createAuthor(authorB);
		underTest.createAuthor(authorC);

		// Buscamos los autores
		List<Author> result = underTest.findAuthors();

		// Test
		assertThat(result)
			.hasSize(3)
			.containsExactly(authorC, authorB, authorA);

	}

	@Test
	public void testThatCanBeCreatedAndUpdatedAuthor() {
		// Creamos un autor
		Author author = TestDataUtil.createTestAuthorA();
		underTest.createAuthor(author);

		// Creamos un nuevo autor que reemplazara el anterior
		Author updatedAuthor = TestDataUtil.createTestAuthorB();
		updatedAuthor.setId(author.getId());

		// Test
		underTest.updateAuthor(updatedAuthor.getId(), updatedAuthor);
		Optional<Author> result = underTest.findOneAuthor(updatedAuthor.getId());

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(updatedAuthor);
	}

	@Test
	public void testThatCanBeCreatedAndDeletedAuthor() {
		// Creamos un autor
		Author author = TestDataUtil.createTestAuthorA();
		underTest.createAuthor(author);

		// Test
		underTest.deleteAuthor(author.getId());
		Optional<Author> result = underTest.findOneAuthor(author.getId());

		assertThat(result).isEmpty();
	}
}
