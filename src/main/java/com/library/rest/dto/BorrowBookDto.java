package com.library.rest.dto;

import lombok.Data;

@Data
public class BorrowBookDto {
    private String title;
    private String author;
    private Long userId;
}