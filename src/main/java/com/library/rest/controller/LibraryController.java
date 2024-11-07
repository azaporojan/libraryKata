package com.library.rest.controller;

import com.library.rest.dto.AddBookDto;
import com.library.rest.dto.BookDto;
import com.library.rest.dto.BorrowBookDto;
import com.library.rest.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("/book")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/create")
    public String addBook(@RequestBody AddBookDto addBookDto) {
        libraryService.addBook(addBookDto);
        return "Book added successfully";
    }

    @PutMapping("/borrow")
    public String borrowBook(@RequestBody BorrowBookDto borrowBookDto) {
        libraryService.borrowBook(borrowBookDto.getTitle(), borrowBookDto.getAuthor(), borrowBookDto.getUserId());
        return "Book borrowed successfully";
    }

    @PutMapping("/return")
    public String returnBook(@RequestBody BorrowBookDto borrowBookDto) {
        libraryService.returnBook(borrowBookDto);
        return "Book returned successfully";
    }

    @GetMapping("/user/{userId}/book")
    public Set<BookDto> getBooksByUser(@PathVariable Long userId) {
        return libraryService.getBooksBorrowedByUser(userId);
    }
}