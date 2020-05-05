package com.todo.modules.task.dto;

import com.todo.modules.account.dto.AccountDto;
import com.todo.modules.task.PriorityType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {

    private Long id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private PriorityType priority;

    private String deadlineDate;

    private String uploadFiles;

    private AccountDto accountDto;
}
