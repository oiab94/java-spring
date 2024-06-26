package com.oiab.firstapi.utils;

import com.oiab.firstapi.dao.Author;
import com.oiab.firstapi.dao.Book;

public class TestDataUtil {
	public static Author createTestAuthorA() {
		return Author.builder()
			.id(1L)
			.name("John Doe")
			.age(30)
			.build();
	}

	public static Author createTestAuthorB() {
		return Author.builder()
			.id(2L)
			.name("Jane Doe")
			.age(25)
			.build();
	}

	public static Author createTestAuthorC() {
		return Author.builder()
			.id(3L)
			.name("John Smith")
			.age(40)
			.build();
	}


	public static Book createTestBookA(final Author author) {
		return Book
			.builder()
			.isbn("1234567890")
			.title("Book Title")
			.author(author)
			.build();
	}

	public static Book createTestBookB(final Author author) {
		return Book
			.builder()
			.isbn("asdfghjkl")
			.title("Book Title 2")
			.author(author)
			.build();
	}

	public static Book createTestBookC(final Author author) {
		return Book
			.builder()
			.isbn("qwertyuiop")
			.title("Book Title 3")
			.author(author)
			.build();
	}
}
