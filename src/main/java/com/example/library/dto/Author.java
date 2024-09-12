package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "Автор", description = "Информация о авторе")
public class Author {
    @Schema(description = "Имя автора")
    @NotNull
    private String name;

    @Schema(description = "Пол автора")
    @NotNull
    private String gender;
}
