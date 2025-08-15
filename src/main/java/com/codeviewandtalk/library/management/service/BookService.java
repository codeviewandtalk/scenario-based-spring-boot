package com.codeviewandtalk.library.management.service;

import com.codeviewandtalk.library.management.dto.BookRequest;
import com.codeviewandtalk.library.management.exception.BookNotFoundException;
import com.codeviewandtalk.library.management.model.Author;
import com.codeviewandtalk.library.management.model.Book;
import com.codeviewandtalk.library.management.repository.AuthorRepository;
import com.codeviewandtalk.library.management.repository.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final EmailService emailService;
    private final AuthorRepository authorRepository;
    public BookService(BookRepository bookRepository, EmailService emailService,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.emailService = emailService;
        this.authorRepository = authorRepository;
    }

    /**
     * Retrieves a list of books by the author's name.
     *
     * @param authorName
     * @return
     */
    @Cacheable(value = "booksByAuthor", key = "#authorName")
    public List<Book> getBooksByAuthorName(String authorName) {
        System.out.println("Fetching books from database " + authorName);
        return bookRepository.findByAuthor_Name(authorName);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id
     * @return
     */

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + id + " not found"));
    }


    public Book addNewBook(BookRequest bookRequest) {

        Book bookObj= new Book();
        bookObj.setTitle(bookRequest.getTitle());
        bookObj.setPublicationDate(bookRequest.getPublicationDate());
        Author author = authorRepository.findById(bookRequest.getAuthorId())
                .orElseThrow(() -> new BookNotFoundException("Author not found"));
        bookObj.setAuthor(author);
        Book savedBook = bookRepository.save(bookObj);
        // Send email notification asynchronously
        emailService.sendEmailNotification(
                "terexo7965@ahvin.com",
                "New Book Added",
                "A new book has been added to the system: " + bookRequest.getTitle()
        );
        return savedBook;
    }
}
