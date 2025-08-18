package com.codeviewandtalk.library.management.repository;

import com.codeviewandtalk.library.management.model.BookCover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCoverRepository extends JpaRepository<BookCover,Long> {
}
