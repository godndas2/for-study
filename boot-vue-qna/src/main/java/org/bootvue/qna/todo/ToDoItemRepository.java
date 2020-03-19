package org.bootvue.qna.todo;

import org.bootvue.qna.todo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoItemRepository extends JpaRepository<TodoItem, String> {
}
