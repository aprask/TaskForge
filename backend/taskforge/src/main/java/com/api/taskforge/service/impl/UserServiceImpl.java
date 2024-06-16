package com.api.taskforge.service.impl;

import com.api.taskforge.model.User;
import com.api.taskforge.repository.UserRepository;
import com.api.taskforge.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        if (user == null) {
            log.error("User: {}, is null", (Object) null);
            return null;
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID userId) {
        if (userId == null) {
            log.error("User ID: {}, is null", (Object) null);
            return null;
        }
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) throws BadRequestException {
        if(validatePageable(pageable)) {
            return userRepository.findAll(pageable);
        }
        throw new BadRequestException("Invalid pageable");
    }

    private boolean validatePageable(Pageable pageable) {
        return pageable.getPageSize() >= 0 && pageable.getOffset() > 0;
    }

    @Override
    public User updateUser(UUID userId, User user) {
        if (userId == null || user == null) {
            log.error("User {} with ID {} is null", userId, user);
            return null;
        }
        User newUser = userRepository.findById(userId).orElse(null);
        if (newUser == null) {
            log.error("User {} with ID {} is null", userId, user);
            return null;
        }
        if (user.getCreatedAt() != null) {
            newUser.setCreatedAt(user.getCreatedAt());
        }
        if (user.getEmail()!= null) {
            newUser.setEmail(user.getEmail());
        }
        if (user.getFirstName()!= null) {
            newUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName()!= null) {
            newUser.setLastName(user.getLastName());
        }
        if (user.getTasks()!= null) {
            newUser.setTasks(user.getTasks());
        }
        newUser.setActive(user.isActive());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID userId) {
        if (userId == null) {
            log.error("User with ID: {}, is null and cannot be deleted", (Object) null);
            return;
        }
        userRepository.deleteById(userId);
    }
}
