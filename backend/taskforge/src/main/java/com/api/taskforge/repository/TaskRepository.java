package com.api.taskforge.repository;

import com.api.taskforge.model.Task;
import com.api.taskforge.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t FROM tasks t WHERE t.user.id =?1")
    Page<Task> findAllByUser(User user, Pageable pageable);
}
