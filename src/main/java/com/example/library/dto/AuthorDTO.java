package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Информация о авторе")
public class AuthorDTO {
    @Schema(description = "Имя автора")
    private String name;
    @Schema(description = "Пол автора")
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
