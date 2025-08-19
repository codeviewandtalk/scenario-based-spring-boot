package com.codeviewandtalk.library.management.controller;

import com.codeviewandtalk.library.management.dto.BookRequest;
import com.codeviewandtalk.library.management.model.Book;
import com.codeviewandtalk.library.management.service.BookArchivingService;
import com.codeviewandtalk.library.management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    private final BookArchivingService archiveService;

    public BookController(BookArchivingService archiveService, BookService bookService) {
        this.bookService = bookService;
        this.archiveService = archiveService;
    }

    /**
     * Retrieves a list of books by the author's name.
     *
     * @param authorName the name of the author
     * @return ResponseEntity containing a list of books or a 204 No Content status if no books are found
     */
    @GetMapping("/by-author")
    //  @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Book>> getBooksByAuthorName(@RequestParam String authorName) {
        List<Book> books = bookService.getBooksByAuthorName(authorName);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book
     * @return ResponseEntity containing the book if found, or a 404 error if not found
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")  // Remove ROLE_ prefix
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/add/book")
    public ResponseEntity<Book> addNewBook( @Valid @RequestBody BookRequest book) {
      Book saveBook = bookService.addNewBook(book);
        return ResponseEntity.ok(saveBook);
    }

    @PostMapping("/trigger")
    public ResponseEntity<String> triggerArchive() {
        archiveService.runArchiveTask();
        return ResponseEntity.ok("Archive task triggered manually.");
    }
}
