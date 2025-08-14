package com.codeviewandtalk.library.management.service;

import com.codeviewandtalk.library.management.model.BookCover;
import com.codeviewandtalk.library.management.repository.BookCoverRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class BookCoverService {
    private final BookCoverRepository repository;

    public BookCoverService(BookCoverRepository repository) {
        this.repository = repository;
    }

    public BookCover storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        byte[] data = file.getBytes();

        BookCover cover = new BookCover(fileName, fileType, data);
        return repository.save(cover);
    }

    public Optional<BookCover> getFile(Long id) {
        return repository.findById(id);
    }
}
