package com.library.persistence.entity;

import com.library.rest.dto.UserBookHistoryDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "user_book_history")
@Getter
@Setter
@NoArgsConstructor
public class UserBookHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_catalog_id")
    private BookCatalog bookCatalogId;

    @Column(name = "date_of_issue", unique = false, nullable = false)
    private LocalDate dateOfIssue;

    @Column(name = "active", unique = false, nullable = false)
    private boolean active;

    public UserBookHistoryDTO toDTO() {
        UserBookHistoryDTO dto = new UserBookHistoryDTO();
        dto.setUser(user.toDTO());
        dto.setBookCatalogId(bookCatalogId.toDTO());
        dto.setActive(active);
        dto.setDateOfIssue(dateOfIssue);
        return dto;
    }
}
