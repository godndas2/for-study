package com.todo.controller;

import com.todo.controller.dto.IdDto;
import com.todo.controller.dto.OverTimeTaskDto;
import com.todo.controller.dto.TaskDto;
import com.todo.domain.PriorityType;
import com.todo.domain.Task;
import com.todo.service.TaskService;
import com.todo.utils.ParseDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskApiController {

    private final TaskService taskService;

    @GetMapping("{/taskId}")
    public ResponseEntity<Task> getTask(@PathVariable("taskId") Long taskId){
        Task task = taskService.getTask(taskId);
        if(task == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Task>> getTasks(@RequestParam(name="page", required = false, defaultValue = "1")int page){
        if(page < 1)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Page<Task> tasks = taskService.getTasks(page - 1);
        if(tasks.getTotalPages() < page)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addTask(@RequestBody TaskDto taskDto){
        Task newTask = mappingTask(taskDto);
        taskService.addTask(newTask);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Task mappingTask(TaskDto taskDto){
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        if(taskDto.getPriority()==null){
            task.setPriority(PriorityType.Low);
        }else{
            task.setPriority(taskDto.getPriority());
        }
        task.setDeadlineDate(ParseDate.stringToDate(taskDto.getDeadlineDate()));
        return task;
    }

    @GetMapping("/overtime")
    public ResponseEntity<OverTimeTaskDto> getOverTimeTasks(){
        List<Task> tasks = taskService.getPastTask();
        OverTimeTaskDto overTimeTaskDto = new OverTimeTaskDto();
        if(tasks.size() == 0){
            overTimeTaskDto.setCount(0);
            return new ResponseEntity<>(overTimeTaskDto, HttpStatus.OK);
        }
        overTimeTaskDto.setCount(tasks.size());
        for(Task t : tasks){
            overTimeTaskDto.addTitle(t.getTitle());
        }
        return new ResponseEntity<>(overTimeTaskDto, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") Long taskId){
        if(taskService.getTask(taskId)==null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        taskService.deleteTask(taskId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity updateTask(@RequestBody TaskDto taskDto){
        if(taskDto.getId() == 0L) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Task updateTask = mappingTask(taskDto);
        Task beforeTask = taskService.getTask(taskDto.getId());
        updateTask.setCreatedDate(beforeTask.getCreatedDate());
        updateTask.setComplete(beforeTask.isComplete());
        if(beforeTask.isTimeOver()){
            if(updateTask.getDeadlineDate().before(new Date())){
                updateTask.setTimeOver(true);
            }
        }
        taskService.updateTask(updateTask);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity changeComplete(@RequestBody IdDto idDto){
        taskService.updateTaskComplete(idDto.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

}
