package com.library.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserBookHistoryDTO {

    private UserDTO user;
    private BookCatalogDTO bookCatalogId;
    private LocalDate dateOfIssue;
    private boolean active;
}
