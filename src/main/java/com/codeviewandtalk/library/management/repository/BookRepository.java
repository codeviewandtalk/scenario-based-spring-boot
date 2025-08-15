package com.codeviewandtalk.library.management.repository;

import com.codeviewandtalk.library.management.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_Name(String name);

    List<Book> findByPublicationDateBefore(LocalDate cutoffDate);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.archived = true WHERE b.publicationDate < CURRENT_DATE - 365")
    int archiveOldBooks();

}
