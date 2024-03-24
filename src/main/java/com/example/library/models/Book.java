package com.example.library.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "Книга")
@Entity
public class Book {
    @Schema(description = "ID книги")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Название книги")
    @NotNull
    private String title;
    @Schema(description = "Авторы книги")
    @ManyToMany()//cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
