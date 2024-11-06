package com.library.rest.advice.exceptions;

import lombok.Getter;

@Getter
public class LibraryManagementException extends RuntimeException {


    public LibraryManagementException(String responseMessage) {
        super(responseMessage);
    }
}
