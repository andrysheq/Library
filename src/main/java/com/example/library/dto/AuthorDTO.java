package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Информация о авторе")
public class AuthorDTO {
    @Schema(description = "Имя автора")
    @NotNull
    private String name;
    @Schema(description = "Пол автора")
    @NotNull
    private String gender;

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
