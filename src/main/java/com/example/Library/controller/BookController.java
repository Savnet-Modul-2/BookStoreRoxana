package com.example.Library.controller;

import com.example.Library.dto.BookDto;
import com.example.Library.dto.validation.ValidationOrder;
import com.example.Library.entities.Book;
import com.example.Library.mapper.BookMapper;
import com.example.Library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/{libraryId}")
    public ResponseEntity<?> createBook(@RequestBody @Validated(ValidationOrder.class) BookDto bookDto,
                                        @PathVariable Long libraryId) {
        Book bookToEntity = BookMapper.toEntity(bookDto);
        Book createdBook = bookService.create(bookToEntity, libraryId);
        BookDto createdBookToDto = BookMapper.toDto(createdBook);
        return ResponseEntity.ok(createdBookToDto);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getById(@PathVariable Long bookId) {
        Book foundBook = bookService.getById(bookId);
        return ResponseEntity.ok(BookMapper.toDto(foundBook));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {

        List<BookDto> books = bookService.findAllBooks(page, size)
                .stream()
                .map(BookMapper::toDto)
                .toList();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/paginated-search")
    public ResponseEntity<?> findBooksPaginated(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title,
            @RequestParam(name = "pageSize", required = false) Integer size,
            @RequestParam(name = "pageNumber", required = false) Integer page) {

        int pageSize = (size != null) ? size : 10;
        int pageNumber = (page != null) ? page : 0;

        Page<Book> foundBooks = bookService.findBooks(author, title, PageRequest.of(page, size));
        Page<BookDto> bookDtos = foundBooks.map(BookMapper::toDto);

        return ResponseEntity.ok(bookDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody @Validated(ValidationOrder.class) BookDto bookDto,
                                        @PathVariable Long id) {
        Book bookEntity = BookMapper.toEntity(bookDto);
        Book updatedBook = bookService.update(bookEntity, id);
        BookDto updatedBookDTO = BookMapper.toDto(updatedBook);

        return ResponseEntity.ok(updatedBookDTO);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId) {
        bookService.removeFromLibrary(bookId);
        return ResponseEntity.noContent().build();
    }
}
