package com.library.rest.dto;

import com.library.persistence.entity.Book;
import lombok.Data;

@Data
public class BookDto {
    private String title;
    private String author;
    private String genre;

    public Book toEntity(){
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setGenre(this.genre);
        book.setIsAvailable(true);
        return book;
    }
}