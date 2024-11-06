package com.library.persistence.repository;

import com.library.persistence.entity.BookCatalog;
import com.library.persistence.entity.User;
import com.library.persistence.entity.UserBookHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserHistoryRepository extends JpaRepository<UserBookHistory, Long> {

    Optional<UserBookHistory> findByUserAndBookCatalogId(User user, BookCatalog bookCatalogId);

    Optional<List<UserBookHistory>> findByUserAndActive(User user, boolean active);
}
