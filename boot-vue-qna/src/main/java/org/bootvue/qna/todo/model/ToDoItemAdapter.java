package org.bootvue.qna.todo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoItemAdapter {

    public static TodoItem toToDoItem(final ToDoItemRequest toDoItemRequest) {
        if(toDoItemRequest == null) {
            return null;
        }
        return TodoItem.builder()
                .title(toDoItemRequest.getTitle())
                .done(toDoItemRequest.isDone())
                .build();
    }

    public static ToDoItemResponse toToDoItemResponse(final TodoItem toDoItem, final List<String> errors) {
        return ToDoItemResponse.builder()
                .todoItem(toDoItem)
                .errors(Optional.ofNullable(errors).orElse(new ArrayList<>()))
                .build();
    }
}
