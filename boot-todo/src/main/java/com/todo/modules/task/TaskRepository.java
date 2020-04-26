package com.todo.modules.task;

import com.todo.modules.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.id =:id")
    public Task getTaskById(@Param("id")Long id);

    @Query("SELECT t FROM Task t WHERE t.timeOver = 1 AND t.complete = 0")
    public List<Task> getTasksByTimeOverIsTrueAndCompleteIsFalse();


    @Query("SELECT t FROM  Task t WHERE t.timeOver = 0")
    public List<Task> getTasksByTimeOver();

    @Modifying
    @Query("DELETE FROM Task t WHERE t.id =:id")
    public void deleteTaskById(@Param("id")Long id);

}

