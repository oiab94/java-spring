package com.oiab.firstapi.repositories;

import com.oiab.firstapi.dao.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
