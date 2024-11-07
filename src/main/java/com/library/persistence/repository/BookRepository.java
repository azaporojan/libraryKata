package com.library.persistence.repository;

import com.library.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleAndAuthor(String title, String author);
}