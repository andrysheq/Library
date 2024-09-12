package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Книга", description = "Информация о книге")
public class Book {
    @Schema(description = "Название книги")
    @NotNull
    private String title;

    @Schema(description = "ID авторов, работавших над книгой")
    @NotNull
    private List<Long> authorIds;
}
