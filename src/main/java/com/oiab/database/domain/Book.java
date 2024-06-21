package com.oiab.database.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
	private Long id;

	private String isbn;

	private String title;

	@ManyToOne(cascade = CascadeType.ALL) // Cascade indica que todas las operaciones que se hagan con author se harán con book
	@JoinColumn(name = "author_id")
	private Author author;
}