package com.library.rest.service;

import com.library.persistence.entity.User;
import com.library.persistence.entity.UserBookHistory;
import com.library.persistence.repository.UserHistoryRepository;
import com.library.persistence.repository.UserRepository;
import com.library.rest.advice.exceptions.LibraryManagementException;
import com.library.rest.dto.UserBookHistoryDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserHistoryServiceImpl implements UserHistoryService {

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserBookHistoryDTO> getBorrowedBooks(Long userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new LibraryManagementException("No user found with id: " + userId));
        return userHistoryRepository.findByUserAndActive(user, true)
                .orElseGet(List::of)
                .stream()
                .map(UserBookHistory::toDTO).toList();
    }
}
