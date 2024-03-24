package com.example.library.controllers;

import com.example.library.dto.AuthorDTO;
import com.example.library.dto.BookDTO;
import com.example.library.models.Author;
import com.example.library.models.Book;
import com.example.library.services.AuthorService;
import com.example.library.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List<Book> books = bookService.readAll();
        //Проверка на пустоту списка
        if (!books.isEmpty()) {
            return ResponseEntity.ok(books);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sorted-by-title")
    @ApiResponse(responseCode = "200", description = "Список книг получен  и отсортирован")
    @ApiResponse(responseCode = "404", description = "Список книг пуст")
    @Operation(summary = "Получить список книг, отсортированный по названию")
    public ResponseEntity<?> getSortedBooksByName() {
        List<Book> books = bookService.readAll();
        if (!books.isEmpty()) {
            books.sort(Comparator.comparing(Book::getTitle));
            return ResponseEntity.ok(books);
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
        Book book = bookService.readById(id);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    @Operation(summary = "Добавить новую книгу")
    @ApiResponse(responseCode = "201", description = "Книга успешно создана")
    @ApiResponse(responseCode = "400", description = "Некорректный запрос")
    public ResponseEntity<Book> createBook(
            @RequestBody BookDTO bookDTO) {
        //Проверка пустоты полей
        if (bookDTO.getAuthorIds() == null || bookDTO.getAuthorIds().isEmpty() || bookDTO.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Book createdBook = bookService.create(bookDTO);
        //Получим null, если авторы, указанные в запросе не будут существовать
        if (createdBook==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdBook);
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
                                          @RequestBody BookDTO bookDTO) {
        //Проверка существует ли книга
        if(!bookService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        //Проверка пустоты полей
        if (bookDTO.getAuthorIds() == null || bookDTO.getAuthorIds().isEmpty() || bookDTO.getTitle().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Book updatedBook = bookService.updateBookInformation(id,bookDTO);
        //Получим null, если авторы, указанные в запросе, не будут существовать
        if (updatedBook==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(updatedBook);
    }
}
