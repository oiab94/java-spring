package com.oiab.firstapi.repositories;

import com.oiab.firstapi.dao.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
