package com.todo.modules.account;

import com.todo.modules.task.Task;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        task.setAccount(this);
        this.taskList.add(task);
    }

    @Builder
    public Account(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
