package com.codeviewandtalk.library.management.service;

import com.codeviewandtalk.library.management.exception.BookNotFoundException;
import com.codeviewandtalk.library.management.model.Book;
import com.codeviewandtalk.library.management.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves a list of books by the author's name.
     * @param authorName
     * @return
     */
    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthor_Name(authorName);
    }

    /**
     * Retrieves a book by its ID.
     * @param id
     * @return
     */

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }
}
