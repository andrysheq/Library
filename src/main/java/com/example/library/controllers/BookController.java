package com.example.library.controllers;

import com.example.library.dto.Book;
import com.example.library.models.BookEntity;
import com.example.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(
    name = "Контроллер для работы с книгами",
    description = "Все методы для работы с книгами"
)
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping()
    @ApiResponse(responseCode = "200", description = "Список книг получен")
    @ApiResponse(responseCode = "404", description = "Список книг пуст")
    @Operation(summary = "Получить список книг")
    public ResponseEntity<?> getBooks() {
        List<BookEntity> bookEntities = bookService.readAll();
        //Проверка на пустоту списка
        if (!bookEntities.isEmpty()) {
            return ResponseEntity.ok(bookEntities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sorted-by-title")
    @ApiResponse(responseCode = "200", description = "Список книг получен  и отсортирован")
    @ApiResponse(responseCode = "404", description = "Список книг пуст")
    @Operation(summary = "Получить список книг, отсортированный по названию")
    public ResponseEntity<?> getSortedBooksByName() {
        List<BookEntity> bookEntities = bookService.readAll();
        if (!bookEntities.isEmpty()) {
            bookEntities.sort(Comparator.comparing(BookEntity::getTitle));
            return ResponseEntity.ok(bookEntities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по ID")
    @ApiResponse(responseCode = "200", description = "Книга найдена")
    @ApiResponse(responseCode = "404", description = "Книга не найдена")
    public ResponseEntity<?> getBookById(
            @Parameter(description = "ID книги")
            @PathVariable Long id) {
        BookEntity bookEntity = bookService.readById(id);
        if (bookEntity != null) {
            return ResponseEntity.ok(bookEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    @Operation(summary = "Добавить новую книгу")
    @ApiResponse(responseCode = "201", description = "Книга успешно создана")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    public ResponseEntity<BookEntity> createBook(
            @RequestBody Book book) {
        //Проверка пустоты полей
        if (book.getAuthorIds() == null || book.getAuthorIds().isEmpty() || book.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        BookEntity createdBookEntity = bookService.create(book);
        //Получим null, если авторы, указанные в запросе не будут существовать
        if (createdBookEntity ==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdBookEntity);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу по ID")
    @ApiResponse(responseCode = "204", description = "Книга успешно удалена")
    @ApiResponse(responseCode = "404", description = "Книга не найдена")
    public ResponseEntity<?> deleteBookById(
            @Parameter(description = "ID книги")
            @PathVariable Long id) {
        if (!bookService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    @Operation(summary = "Удалить все книги")
    @ApiResponse(responseCode = "204", description = "Книги успешно удалены")
    @ApiResponse(responseCode = "404", description = "Книги не найдены")
    public ResponseEntity<?> deleteAllBooks() {
        if (bookService.readAll().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        bookService.clear();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "201", description = "Информация о книге обновлена")
    @ApiResponse(responseCode = "404", description = "Книга не найдена")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    @Operation(summary = "Обновить информацию о книге")
    public ResponseEntity<?> updateBook(@Parameter(description = "ID книги")
                                          @PathVariable Long id,
                                          @RequestBody Book book) {
        //Проверка существует ли книга
        if(!bookService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        //Проверка пустоты полей
        if (book.getAuthorIds() == null || book.getAuthorIds().isEmpty() || book.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        BookEntity updatedBookEntity = bookService.updateBookInformation(id, book);
        //Получим null, если авторы, указанные в запросе, не будут существовать
        if (updatedBookEntity ==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updatedBookEntity);
    }
}
