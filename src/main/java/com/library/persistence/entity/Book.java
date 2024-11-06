package com.library.persistence.entity;

import com.library.rest.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = false, nullable = false)
    private String title;

    @Column(name = "author", unique = false, nullable = false)
    private String author;

    @Column(name = "genre", unique = false, nullable = false)
    private String genre;

    public BookDTO toDTO(){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(this.title);
        bookDTO.setAuthor(this.author);
        bookDTO.setGenre(this.genre);
        return bookDTO;
    }
}
