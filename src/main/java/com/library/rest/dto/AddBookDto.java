package com.library.rest.dto;

import lombok.Data;

@Data
public class AddBookDto {
    private BookDto book;
    private Integer quantity;
}