package org.bootvue.qna.todo;

import lombok.RequiredArgsConstructor;
import org.bootvue.qna.todo.model.TodoItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    // Validation
    public TodoItem get(final String id) {
        return toDoItemRepository.findById(id).orElse(null);
    }

    public TodoItem create(final TodoItem todoItem) {
        if (todoItem == null) {
            throw new NullPointerException("TodoItem can not be null");
        }
        return toDoItemRepository.save(todoItem);
    }

    public List<TodoItem> findAll() {
        return toDoItemRepository.findAll();
    }
}
