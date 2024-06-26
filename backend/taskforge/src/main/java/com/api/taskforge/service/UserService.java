package com.api.taskforge.service;

import com.api.taskforge.model.User;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    Page<User> getAllUsers(Pageable pageable) throws BadRequestException;

    User updateUser(Long userId, User updatedUser);

    void deleteUser(Long userId);

    Optional<User> getUserByEmail(String email);

}