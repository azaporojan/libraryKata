package com.library.rest.dto;

import lombok.Data;

@Data
public class BookCatalogDTO {
    private Long id;
    private BookDTO book;
    private boolean available;
}

