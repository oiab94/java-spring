//package com.oiab.database;
//
//import com.oiab.database.domain.Author;
//import com.oiab.database.domain.Book;
//
//public final class TestDataUtil {
//	private TestDataUtil() {}
//
//	public static Author createTestAuthorA() {
//		return Author.builder()
//			.id(1L)
//			.name("John Doe")
//			.age(30)
//			.build();
//	}
//
//	public static Author createTestAuthorB() {
//		return Author.builder()
//			.id(2L)
//			.name("Jane Doe")
//			.age(25)
//			.build();
//	}
//
//	public static Author createTestAuthorC() {
//		return Author.builder()
//			.id(3L)
//			.name("John Smith")
//			.age(40)
//			.build();
//	}
//
//
//	public static Book createTestBookA() {
//		return Book
//			.builder()
//			.id(1L)
//			.isbn("1234567890")
//			.title("Book Title")
//			.authorId(1L)
//			.build();
//	}
//
//	public static Book createTestBookB() {
//		return Book
//			.builder()
//			.id(2L)
//			.isbn("asdfghjkl")
//			.title("Book Title 2")
//			.authorId(1L)
//			.build();
//	}
//
//	public static Book createTestBookC() {
//		return Book
//			.builder()
//			.id(3L)
//			.isbn("qwertyuiop")
//			.title("Book Title 3")
//			.authorId(1L)
//			.build();
//	}
//}