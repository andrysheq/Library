package com.example.library.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Информация о книге")
public class BookDTO {
    @Schema(description = "название книги")
    private String title;
    @Schema(description = "ID авторов, работавших над книгой")
    private List<Long> authorIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Long> authorIds) {
        this.authorIds = authorIds;
    }
}
