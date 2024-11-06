package com.library.rest.service;

import com.library.rest.dto.UserBookHistoryDTO;

import java.util.List;

public interface UserHistoryService {
    List<UserBookHistoryDTO> getBorrowedBooks(Long userId);

}
