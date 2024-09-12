package com.example.library.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(name = "Автор")
@Entity
@Table(name = "author")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthorEntity {
    @Schema(description = "ID автора")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Имя автора")
    @NotNull
    private String name;

    @Schema(description = "Пол автора")
    @NotNull
    private String gender;
}
