package com.library.persistence.entity;

import com.library.rest.dto.BookCatalogDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "book_catalog")
public class BookCatalog {
    @Id
    @Column(name = "book_catalog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "is_available")
    private boolean available;

    public BookCatalogDTO toDTO(){
        BookCatalogDTO dto = new BookCatalogDTO();
        dto.setId(id);
        dto.setBook(book.toDTO());
        dto.setAvailable(available);
        return dto;
    }

}
