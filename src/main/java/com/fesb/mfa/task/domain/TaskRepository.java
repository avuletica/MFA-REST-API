package com.fesb.mfa.task.domain;

import com.fesb.mfa.task.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
