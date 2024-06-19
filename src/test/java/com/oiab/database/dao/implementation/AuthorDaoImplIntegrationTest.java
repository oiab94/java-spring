package com.oiab.database.dao.implementation;

import com.oiab.database.TestDataUtil;
import com.oiab.database.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
		Author author = TestDataUtil.createTestAuthor();
		underTest.createAuthor(author);

		// Failed Test
		//Optional<Author> result = underTest.findOneAuthor(99L);
		Optional<Author> result = underTest.findOneAuthor(author.getId());

		assertThat(result).isPresent();
		assertThat(result.get()).isEqualTo(author);
	}
}
