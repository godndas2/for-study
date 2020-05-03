package com.todo.modules.task;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task getTask(Long taskId){
        return taskRepository.getTaskById(taskId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Task> getTasks(int page){
        Sort sort = Sort.by(
                Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, 5, sort);
        return taskRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getPastTask(){
        return taskRepository.getTasksByTimeOverIsTrueAndCompleteIsFalse();
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    @Transactional
    public void updateTask(Task task){
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void updateTaskComplete(Long taskId){
        Task task = taskRepository.findById(taskId).get();
        task.setComplete(task.isComplete()? false : true);
    }

    @Override
    @Transactional
    public void updateTaskTimeOver(Long taskId){
        Task task = taskRepository.findById(taskId).get();
        task.setTimeOver(task.isTimeOver()? false : true);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getTasksByTimeOver(){
        return taskRepository.getTasksByTimeOver();
    }
}