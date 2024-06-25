package com.oiab.database.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authors")
public class Author {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
	private Long id;

	private String name;

	private Integer age;
}