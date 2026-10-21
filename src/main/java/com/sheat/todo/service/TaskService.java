package com.sheat.todo.service;

import com.sheat.todo.entity.Task;
import com.sheat.todo.entity.TaskStatus;
import com.sheat.todo.exception.TaskNotFoundException;
import com.sheat.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public List<Task> getTasksByDate(LocalDate date) {
        return taskRepository.findByTaskDate(date);
    }

    public Task updateTask(Long id, Task newTask) {
        Task task = getTaskById(id);

        task.setTitle(newTask.getTitle());
        task.setDescription(newTask.getDescription());
        task.setTaskDate(newTask.getTaskDate());
        task.setTaskTime(newTask.getTaskTime());
        task.setStatus(newTask.getStatus());

        return taskRepository.save(task);
    }

    public Task updateStatus(Long id, TaskStatus status) {
        Task task = getTaskById(id);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
