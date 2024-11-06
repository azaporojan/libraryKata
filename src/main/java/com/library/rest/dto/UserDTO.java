package com.library.rest.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String address;
    private String passportNumber;
}
