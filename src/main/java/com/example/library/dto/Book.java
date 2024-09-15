package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Книга", description = "Информация о книге")
public class Book extends BaseDomain {

    @Schema(description = "Название книги")
    @NotNull
    private String title;

    @Schema(description = "Количество страниц")
    @NotNull
    private Integer pageAmount;

    @Schema(description = "ID авторов, работавших над книгой")
    @NotNull
    private Set<Long> authorIds;
}
