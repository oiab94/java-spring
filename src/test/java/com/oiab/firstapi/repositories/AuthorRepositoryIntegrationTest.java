package com.oiab.firstapi.repositories;

import com.oiab.firstapi.dao.Author;
import com.oiab.firstapi.utils.TestDataUtil;
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
public class AuthorRepositoryIntegrationTest {
	private AuthorRepository underTest;

	@Autowired
	public AuthorRepositoryIntegrationTest(final AuthorRepository underTest) {
		this.underTest = underTest;
	}


	@Test
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
}
