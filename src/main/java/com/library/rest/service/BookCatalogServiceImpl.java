package com.library.rest.service;

import com.library.persistence.entity.Book;
import com.library.persistence.entity.BookCatalog;
import com.library.persistence.entity.User;
import com.library.persistence.entity.UserBookHistory;
import com.library.persistence.repository.BookRepository;
import com.library.persistence.repository.CatalogRepository;
import com.library.persistence.repository.UserHistoryRepository;
import com.library.persistence.repository.UserRepository;
import com.library.rest.advice.exceptions.LibraryManagementException;
import com.library.rest.dto.BookDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BookCatalogServiceImpl implements BookCatalogService {

    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private BookRepository bookRepository;

    @Override
    public String borrowBook(Long userId, Long bookId) {
        UserBookHistory userHistory = new UserBookHistory();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new LibraryManagementException("No user was found with id: " + userId));
        userHistory.setUser(user);

        BookCatalog catalog = catalogRepository.findByBookId(bookId)
                .orElseThrow(() -> new LibraryManagementException("No book was found with id: " + bookId));

        if (catalog.isAvailable()) {
            catalog.setAvailable(false);
            catalogRepository.save(catalog);
            userHistory.setDateOfIssue(LocalDate.now());
            userHistory.setBookCatalogId(catalog);
            userHistory.setActive(true);
            userHistoryRepository.save(userHistory);
        }
        return "The Book was borrowed successfully";
    }

    @Override
    public String returnBook(Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new LibraryManagementException("No user was found with id: " + userId));

        UserBookHistory userHistory = userHistoryRepository.findByUserAndBookCatalogId(user, catalogRepository.findByBookId(bookId).get())
                .orElseThrow(() -> new LibraryManagementException("No book was borrowed with id: " + bookId));
        userHistory.setUser(user);
        userHistory.setBookCatalogId(catalogRepository.findByBookId(bookId).get());
        userHistory.setDateOfIssue(LocalDate.now());
        userHistory.setActive(false);
        userHistoryRepository.save(userHistory);

        BookCatalog catalog = catalogRepository.findByBookId(bookId).get();
        catalog.setAvailable(true);
        catalogRepository.save(catalog);

        return "Book successfully returned";
    }

    @Override
    public void addNewBook(BookDTO bookDTO) {
        Book book = bookRepository.findOne(Example.of(bookDTO.toEntity()))
                .orElseGet(() -> bookRepository.save(bookDTO.toEntity()));
        for (int i = 0; i < bookDTO.getQuantity(); i++) {
            BookCatalog catalog = new BookCatalog();
            catalog.setAvailable(true);
            catalog.setBook(book);
            catalogRepository.save(catalog);
        }
    }
}
