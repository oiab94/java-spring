DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
    id      SERIAL PRIMARY KEY,
    name    TEXT,
    age     INTEGER
);

CREATE TABLE books (
    id          SERIAL PRIMARY KEY,
    isbn        TEXT,
    title       TEXT,
    author_id   BIGINT CONSTRAINT fk_books_authros_id REFERENCES authors(id) ON DELETE CASCADE
);