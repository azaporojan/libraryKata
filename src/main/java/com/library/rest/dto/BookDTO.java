package com.library.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.library.persistence.entity.Book;
import lombok.Data;

@Data

public class BookDTO {
    private String title;
    private String author;
    private String genre;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer quantity;

    public Book toEntity() {
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setGenre(this.genre);
        return book;
    }
}
