package com.library.persistence.entity;

import com.library.rest.dto.BookDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "genre")
    private String genre;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre) && Objects.equals(isAvailable, book.isAvailable) && Objects.equals(user.getId(), book.user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, isAvailable, user.getId());
    }

    public BookDto toDTO() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setGenre(this.genre);
        return bookDto;
    }
}