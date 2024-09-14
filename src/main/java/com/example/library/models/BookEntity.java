package com.example.library.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Schema(name = "Книга")
@Entity
@Table(name = "book")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@RequiredArgsConstructor
public class BookEntity {
    @Schema(description = "ID книги")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Название книги")
    @NotNull
    private String title;

    @Schema(description = "Авторы книги")
    @ManyToMany()
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> authorEntities;
}
