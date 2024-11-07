package com.library.rest.service;

import com.library.persistence.entity.Book;
import com.library.persistence.repository.BookRepository;
import com.library.rest.advice.exceptions.LibraryManagementException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Data
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        Book book = new Book();
        book.setTitle("The Hobbit");
        book.setAuthor("J.R.R. Tolkien");
        book.setGenre("Fantasy");
        book.setIsAvailable(true);
        bookRepository.save(book);

        Book book1 = new Book();
        book1.setTitle("The Lord of the Rings");
        book1.setAuthor("J.R.R. Tolkien");
        book1.setGenre("Fantasy");
        book1.setIsAvailable(true);
        bookRepository.save(book1);

    }

    public Book getBookIfAvailable(String title, String author) {
        return getBooksByTitleAndAuthor(title, author).stream()
                .filter(Book::getIsAvailable).findFirst()
                .orElseThrow(() -> new LibraryManagementException("No books available"));
    }

    public List<Book> getBooksByTitleAndAuthor(String title, String author) {
        List<Book> booksFound = bookRepository.findByTitleAndAuthor(title, author);
        if (booksFound.isEmpty()) {
            throw new LibraryManagementException(format("No books found with title: %s and author: %s", title, author));
        }
        return booksFound;
    }
}