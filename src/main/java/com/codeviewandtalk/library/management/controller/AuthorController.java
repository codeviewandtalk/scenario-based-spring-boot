package com.codeviewandtalk.library.management.controller;

import com.codeviewandtalk.library.management.model.Author;
import com.codeviewandtalk.library.management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorRepository.save(author);
    }
}
