package com.codeviewandtalk.library.management.repository;

import com.codeviewandtalk.library.management.model.Book;
import io.lettuce.core.dynamic.annotation.Param;
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

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.archived = true WHERE b.publicationDate < :cutoffDate")
    int archiveOldBooks(@Param("cutoffDate") LocalDate cutoffDate);
}