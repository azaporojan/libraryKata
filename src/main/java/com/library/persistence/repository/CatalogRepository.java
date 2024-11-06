package com.library.persistence.repository;

import com.library.persistence.entity.BookCatalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<BookCatalog, Long> {

    Optional<BookCatalog> findByBookId(Long bookId);
}
