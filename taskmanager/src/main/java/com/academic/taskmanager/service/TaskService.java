package com.academic.taskmanager.service;
import com.academic.taskmanager.entity.Task;
import com.academic.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }
    public List getAllTask(){
        return taskRepository.findAll();
    }
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with ID"+id));
    }
    public Task createTask(Task task){
        return taskRepository.save(task);
    }
    public Task updateTask(Long id,Task updateTask){
        Task existingTask=getTaskById(id);
        existingTask.setTitle(updateTask.getTitle());
        existingTask.setSubject(updateTask.getSubject());
        existingTask.setDeadLine(updateTask.getDeadLine());
        existingTask.setCompleted(updateTask.isCompleted());
        return taskRepository.save(existingTask);
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
