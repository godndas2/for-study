package org.bootvue.qna.todo.model;

import lombok.Builder;
import org.bootvue.qna.response.ApiResponse;

import java.util.List;

public class ToDoItemResponse extends ApiResponse<TodoItem> {

    @Builder
    private ToDoItemResponse(final TodoItem todoItem, final List<String> errors) {
        this.setData(todoItem);
        this.setErrors(errors);
    }
}
