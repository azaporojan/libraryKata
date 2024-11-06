package com.library.rest.controller;

import com.library.rest.dto.UserBookHistoryDTO;
import com.library.rest.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    UserHistoryService userHistoryService;

    @GetMapping("/user/{userId}/borrowed")
    public List<UserBookHistoryDTO> getBorrowedBooks(@PathVariable Long userId) {
        return userHistoryService.getBorrowedBooks(userId);
    }

}
