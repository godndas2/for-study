package com.todo.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.modules.task.Task;
import com.todo.modules.task.TaskController;
import com.todo.modules.task.TaskRepository;
import com.todo.modules.task.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
public class TaskControllerTest {

    @Autowired
    private TaskController taskController;
    @Autowired
    private TaskRepository taskRepository;
    @MockBean
    private TaskService taskService;

    private MockMvc mockMvc;
    private List<Task> tasks;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(taskController)
                .build();

        tasks = new ArrayList<>();

        Task task = new Task();
        task.setId(1L);
        task.setTitle("TestTitleForTASK");
        task.setDescription("TEST....");
        tasks.add(task);

        task = new Task();
        task.setId(2L);
        task.setTitle("TestTitleForTASK222");
        task.setDescription("TEST222....");
        tasks.add(task);
    }

    @AfterEach
    public void tearDown() {
        taskRepository.deleteAll();
    }

    @Test
    public void addTaskSuccess() throws Exception {
        Task newTask = tasks.get(0);
        newTask.setId(999L);
        ObjectMapper objectMapper = new ObjectMapper();
        String writeValueAsTask = objectMapper.writeValueAsString(newTask);
        this.mockMvc.perform(post("/api/tasks")
        .content(writeValueAsTask)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isCreated());
    }


}
