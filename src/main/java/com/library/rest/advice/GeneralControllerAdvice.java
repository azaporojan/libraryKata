package com.library.rest.advice;

import com.library.rest.advice.exceptions.LibraryManagementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GeneralControllerAdvice {


    @ExceptionHandler(LibraryManagementException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        return "An Exemption occurred while executing your request " + e.getMessage();
    }

}
