package org.bootvue.qna.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bootvue.qna.todo.model.ToDoItemAdapter;
import org.bootvue.qna.todo.model.ToDoItemRequest;
import org.bootvue.qna.todo.model.ToDoItemResponse;
import org.bootvue.qna.todo.model.TodoItem;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class ToDoItemController {

    private final ToDoItemService itemService;

    @PostMapping
    public @ResponseBody ToDoItemResponse create(@RequestBody @Valid final ToDoItemRequest request) {
        List<String> errors = new ArrayList<>();
        TodoItem todoItem = ToDoItemAdapter.toToDoItem(request);
        log.debug(request.getTitle());
        try{
            todoItem = itemService.create(todoItem);
        } catch (final Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
        }
        return ToDoItemAdapter.toToDoItemResponse(todoItem, errors);
    }
}
