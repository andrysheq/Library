package com.example.library.controllers;

import com.example.library.dto.AuthorDTO;
import com.example.library.models.Author;
import com.example.library.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(
        name = "Контроллер для работы с авторами",
        description = "Все методы для работы с авторами"
)
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    @Operation(summary = "Получить список всех авторов")
    @ApiResponse(responseCode = "200", description = "Список авторов получен")
    @ApiResponse(responseCode = "404", description = "Список авторов пуст")
    public ResponseEntity<List<Author>> getAuthors() {
        List<Author> authors = authorService.readAll();
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Автор найден")
    @ApiResponse(responseCode = "404", description = "Автор не найден")
    @Operation(summary = "Получить автора по его ID")
    public ResponseEntity<Author> getAuthorById(
            @Parameter(description = "ID автора")
            @PathVariable Long id) {
        if (authorService.existsById(id)) {
            return ResponseEntity.ok(authorService.readById(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sorted-by-name")
    @ApiResponse(responseCode = "200", description = "Список авторов получен и отсортирован")
    @ApiResponse(responseCode = "404", description = "Список авторов пуст")
    @Operation(summary = "Получить список авторов отсортированный по имени")
    public ResponseEntity<?> getSortedAuthorsByName() {
        List<Author> authors = authorService.readAll();
        //Проверка на пустоту списка авторов
        if (!authors.isEmpty()) {
            authors.sort(Comparator.comparing(Author::getName));
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sorted-by-gender")
    @ApiResponse(responseCode = "200", description = "Список авторов получен и отсортирован")
    @ApiResponse(responseCode = "404", description = "Список авторов пуст")
    @Operation(summary = "Получить список авторов отсортированный по полу")
    public ResponseEntity<?> getSortedAuthorsByGender() {
        List<Author> authors = authorService.readAll();
        //Проверка на пустоту списка авторов
        if (!authors.isEmpty()) {
            authors.sort(Comparator.comparing(Author::getGender));
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    @ApiResponse(responseCode = "201", description = "Автор успешно добавлен")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    @Operation(summary = "Добавить нового автора")
    public ResponseEntity<?> createAuthor(
            @RequestBody AuthorDTO authorDTO) {
        //Проверка пустоты полей
        if (authorDTO.getGender().isEmpty() || authorDTO.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authorService.create(authorDTO));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Автор успешно удален")
    @ApiResponse(responseCode = "404", description = "Автор не найден")
    @Operation(summary = "Удалить автора по ID")
    public ResponseEntity<?> deleteAuthorById(
            @Parameter(description = "ID автора")
            @PathVariable Long id) {
        //Проверка существует ли автор
        if (!authorService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search/by-name")
    @Operation(summary = "Поиск авторов по имени")
    @ApiResponse(responseCode = "200", description = "Авторы найдены")
    @ApiResponse(responseCode = "404", description = "Авторы не найдены")
    public ResponseEntity<?> searchAuthorByName(
            @Parameter(description = "Имя автора")
            @RequestParam String name) {
        List<Author> authors = authorService.readByName(name);
        //Проверка на пустоту списка авторов
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/")
    @ApiResponse(responseCode = "204", description = "Авторы успешно удалены")
    @ApiResponse(responseCode = "404", description = "Авторы не найдены")
    @Operation(summary = "Удалить всех авторов")
    public ResponseEntity<?> deleteAllAuthors() {
        //Проверка на пустоту списка авторов
        if (authorService.readAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        authorService.clear();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Информация об авторе обновлена")
    @ApiResponse(responseCode = "404", description = "Автор не найден")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    @Operation(summary = "Обновить информацию об авторе")
    public ResponseEntity<?> updateAuthor(@Parameter(description = "ID автора")
                                                  @PathVariable Long id,
                                                  @RequestBody AuthorDTO authorDTO) {
        //Проверка на существование автора
        if(!authorService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        //Проверка на пустоту полей
        if (authorDTO.getGender().isEmpty() || authorDTO.getName().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authorService.updateAuthorInformation(id,authorDTO));
    }
}
