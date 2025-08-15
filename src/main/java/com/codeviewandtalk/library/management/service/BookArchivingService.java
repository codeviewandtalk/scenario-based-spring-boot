package com.codeviewandtalk.library.management.service;

import com.codeviewandtalk.library.management.model.ArchiveAuditLog;
import com.codeviewandtalk.library.management.model.Book;
import com.codeviewandtalk.library.management.repository.ArchiveAuditLogRepository;
import com.codeviewandtalk.library.management.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class BookArchivingService {
    private final ArchiveAuditLogRepository logRepository;
    private final BookRepository bookRepository;

    public BookArchivingService(ArchiveAuditLogRepository logRepository, BookRepository bookRepository) {
        this.logRepository = logRepository;
        this.bookRepository = bookRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs every midnight
    public void archiveOldBooks() {
        runArchiveTask();
    }


    public void runArchiveTask() {
        LocalDateTime runTime = LocalDateTime.now();
        try {
            LocalDate cutoffDate = LocalDate.now().minusYears(1); // Books older than 1 year
            int archivedCount = bookRepository.archiveOldBooks(cutoffDate);
            logRepository.save(new ArchiveAuditLog(runTime, archivedCount, "SUCCESS", "Archived successfully"));
        } catch (Exception e) {
            logRepository.save(new ArchiveAuditLog(runTime, 0, "FAILED", e.getMessage()));
        }
    }
}
