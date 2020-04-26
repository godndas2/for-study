package com.todo.controller.dto;

import com.todo.domain.PriorityType;
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
