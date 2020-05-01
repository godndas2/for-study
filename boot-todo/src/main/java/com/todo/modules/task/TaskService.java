package com.todo.modules.task;

import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    public Task getTask(Long taskId);
    public Page<Task> getTasks(int Page);
    public Task addTask(Task task);
    public List<Task> getPastTask();
    public void deleteTask(Long taskId);
    public void updateTask(Task task);
    public void updateTaskComplete(Long taskId);
    public void updateTaskTimeOver(Long taskId);
    public List<Task> getTasksByTimeOver();

}
