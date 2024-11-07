package com.library.rest.service;

import com.library.persistence.entity.Book;
import com.library.persistence.entity.User;
import com.library.rest.advice.exceptions.LibraryManagementException;
import com.library.rest.dto.AddBookDto;
import com.library.rest.dto.BookDto;
import com.library.rest.dto.BorrowBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class LibraryService {


    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    public void borrowBook(String title, String author, Long userId) {
        Book book = bookService.getBookIfAvailable(title, author);
        User user = userService.getUserById(userId);
        book.setIsAvailable(false);
        book.setUser(user);
        Set<Book> booksBorrowedByUser = Objects.isNull(user.getBorrowedBooks()) ? new HashSet<>() : user.getBorrowedBooks();
        booksBorrowedByUser.add(book);
        user.setBorrowedBooks(booksBorrowedByUser);
        userService.getUserRepository().save(user);
        bookService.getBookRepository().save(book);
    }

    public void returnBook(BorrowBookDto borrowBookDto) {
        User user = userService.getUserById(borrowBookDto.getUserId());
        Book bookBorrowedByUser = user.getBorrowedBooks().stream()
                .filter(book -> book.getTitle().equals(borrowBookDto.getTitle()) && book.getAuthor().equals(borrowBookDto.getAuthor()))
                .findFirst()
                .orElseThrow(() -> new LibraryManagementException("Book not borrowed by user"));
        user.getBorrowedBooks().remove(bookBorrowedByUser);
        bookBorrowedByUser.setIsAvailable(true);
        userService.getUserRepository().save(user);
    }

    public Set<BookDto> getBooksBorrowedByUser(Long userId) {
        return userService.getUserById(userId).getBorrowedBooks().stream().map(Book::toDTO).collect(Collectors.toSet());
    }

    public void addBook(AddBookDto addBookDto) {
        IntStream.range(0, addBookDto.getQuantity()).forEach(i -> bookService.getBookRepository().save(addBookDto.getBook().toEntity()));
    }
}