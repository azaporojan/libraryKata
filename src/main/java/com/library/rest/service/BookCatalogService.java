package com.library.rest.service;

import com.library.rest.dto.BookDTO;

public interface BookCatalogService {
    String borrowBook(Long userId, Long bookId);
    String returnBook(Long userId, Long bookId);
    void addNewBook(BookDTO bookDTO);
}
