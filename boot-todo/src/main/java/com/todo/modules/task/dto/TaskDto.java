package com.todo.modules.task.dto;

import com.todo.modules.task.PriorityType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TaskDto {

    private Long id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private PriorityType priority;

    private String deadlineDate;
}
