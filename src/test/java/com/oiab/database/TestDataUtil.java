package com.oiab.database;

import com.oiab.database.domain.Author;
import com.oiab.database.domain.Book;

public final class TestDataUtil {
	private TestDataUtil() {}

	public static Author createTestAuthor() {
		return Author.builder()
			.id(1L)
			.name("John Doe")
			.age(30)
			.build();
	}

	public static Book createTestBook() {
		return Book
			.builder()
			.id(1L)
			.isbn("1234567890")
			.title("Book Title")
			.authorId(1L)
			.build();
	}
}
