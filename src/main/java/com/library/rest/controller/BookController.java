package com.library.rest.controller;

import com.library.rest.dto.BookDTO;
import com.library.rest.dto.BookActivityDTO;
import com.library.rest.service.BookCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
public class BookController {
    @Autowired
    private BookCatalogService catalogService;

    @PutMapping("/book/borrow")
    public String updateBook(@RequestBody BookActivityDTO bookDTO) {
        return catalogService.borrowBook(bookDTO.getUserId(), bookDTO.getBookId());
    }

    @PutMapping("/book/return")
    public String returnBook(@RequestBody BookActivityDTO bookDTO) {
        return catalogService.returnBook(bookDTO.getUserId(), bookDTO.getBookId());
    }

    @PostMapping("/book/add")
    @ResponseStatus(HttpStatus.OK)
    public void addBook(@RequestBody BookDTO bookDTO) {
        catalogService.addNewBook(bookDTO);
    }
}
