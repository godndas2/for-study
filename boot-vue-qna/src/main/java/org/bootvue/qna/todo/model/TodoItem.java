package org.bootvue.qna.todo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoItem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String id;

    private String title;
    private boolean done;
}
