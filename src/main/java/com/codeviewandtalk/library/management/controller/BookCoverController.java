package com.codeviewandtalk.library.management.controller;

import com.codeviewandtalk.library.management.model.BookCover;
import com.codeviewandtalk.library.management.service.BookCoverService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/books")
public class BookCoverController {

    private final BookCoverService service;

    public BookCoverController(BookCoverService service) {
        this.service = service;
    }

    // Upload
    @PostMapping("/upload-cover")
    public ResponseEntity<String> uploadCover(@RequestParam("file") MultipartFile file) {
        try {
            BookCover savedFile = service.storeFile(file);
            return ResponseEntity.ok("File uploaded successfully with ID: " + savedFile.getId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("File upload failed");
        }
    }

    // Download
    @GetMapping("/download-cover/{id}")
    public ResponseEntity<byte[]> downloadCover(@PathVariable Long id) {
        return service.getFile(id)
                .map(file -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                        .body(file.getData()))
                .orElse(ResponseEntity.notFound().build());
    }

}
