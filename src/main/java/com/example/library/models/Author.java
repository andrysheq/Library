package com.example.library.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Schema(description = "Автор")
@Entity
public class Author {
    @Schema(description = "ID автора")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(description = "Имя автора")
    private String name;
    @Schema(description = "Пол автора")
    private String gender;
    @Schema(description = "Книги автора")
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
    private List<Book> books;

    public Author() {
    }

    public Author(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
