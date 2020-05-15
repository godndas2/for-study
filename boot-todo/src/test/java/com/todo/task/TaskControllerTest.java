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
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @Description("addTask")
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

    @Test
    @Description("updateTask")
    public void updateTaskSuccess() throws Exception {
        given(this.taskService.getTask(1L)).willReturn(tasks.get(0));
        Task newTask = tasks.get(0);
        newTask.setTitle("UpdateTitle");
        newTask.setDescription("UpdateDescription");
        ObjectMapper objectMapper = new ObjectMapper();
        String writeValueAsString = objectMapper.writeValueAsString(newTask);

        this.mockMvc.perform(put("/api/tasks/1")
                .content(writeValueAsString)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
