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

    public TodoItem update(final TodoItem toDoItem) {
        if (toDoItem == null) {
            throw new NullPointerException("To Do Item cannot be null");
        }
        final TodoItem original = toDoItemRepository.findById(toDoItem.getId())
                .orElseThrow(() -> new RuntimeException("Entity Not Found"));
        original.setTitle(toDoItem.getTitle());
        original.setDone(toDoItem.isDone());
        return toDoItemRepository.save(original);
    }
}
