package com.todo.modules.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.todo.modules.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private PriorityType priority;

    @Column(name = "created_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date createdDate;

    @Column(name = "deadline_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Date deadlineDate;

    @Column
    private boolean complete;

    @Column
    private boolean timeOver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void addAccount(Account account) {
        // 영속성 컨텍스트 Memory 신뢰성을 위해
        if (this.account != null)
            this.account.getTaskList().remove(this);
        this.account = account;
        account.getTaskList().add(this);
    }

    public Task(){
        createdDate = new Date();
        deadlineDate = new Date();
        complete = false;
        timeOver = false;
    }

    public Task(Date deadlineDate){
        createdDate = new Date();
        deadlineDate = deadlineDate;
        complete = false;
        timeOver = false;
    }

}
