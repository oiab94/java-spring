package com.oiab.firstapi.repositories;

import com.oiab.firstapi.dao.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	@Query(value = "SELECT a FROM Author a WHERE age < ?1")
	Iterable<Author> findByAgeLessThan(int age);

	@Query(value = "SELECT a FROM Author a WHERE age > ?1")
	Iterable<Author> findByAgeGreaterThan(int age);

	@Query(value = "SELECT a FROM Author a ORDER BY id ASC")
	Iterable<Author> findAllByIdAsc();
}
