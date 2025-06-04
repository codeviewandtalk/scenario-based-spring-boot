package com.codeviewandtalk.library.management.service;

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

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthor_Name(authorName);
    }
}
