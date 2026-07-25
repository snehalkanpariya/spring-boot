package com.academic.taskmanager.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.academic.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    
} 