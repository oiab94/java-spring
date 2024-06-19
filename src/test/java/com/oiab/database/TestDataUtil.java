package com.oiab.database;

import com.oiab.database.domain.Author;

public final class TestDataUtil {
	private TestDataUtil() {}

	public static Author createTestAuthor() {
		return Author.builder()
			.id(1L)
			.name("John Doe")
			.age(30)
			.build();
	}
}
