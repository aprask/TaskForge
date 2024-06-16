package com.api.taskforge.service;

import com.api.taskforge.model.User;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {

    User createUser(User user);

    User getUserById(UUID userId);

    Page<User> getAllUsers(Pageable pageable) throws BadRequestException;

    User updateUser(UUID userId, User updatedUser);

    void deleteUser(UUID userId);

}