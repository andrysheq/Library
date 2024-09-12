DROP TABLE IF EXISTS book_authors;
DROP TABLE IF EXISTS bookEntity;
DROP TABLE IF EXISTS authorEntity;

CREATE TABLE authorEntity (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(255) NOT NULL
);

CREATE TABLE bookEntity (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE book_authors (
      book_id bigint NOT NULL,
      author_id bigint NOT NULL,
      CONSTRAINT FK551i3sllw1wj7ex6nir16blsm FOREIGN KEY (author_id) REFERENCES authorEntity,
      CONSTRAINT FKs4xm7q8i3uxvaiswj1c35nnxw FOREIGN KEY (book_id) REFERENCES bookEntity
);

ALTER TABLE authorEntity ALTER COLUMN id TYPE bigint;
ALTER TABLE bookEntity ALTER COLUMN id TYPE bigint;


