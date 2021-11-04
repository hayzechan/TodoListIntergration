package com.todolist.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.demo.entity.TodoItem;
import com.todolist.demo.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TodoItemControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TodoListRepository todoListRepository;

    @BeforeEach
    void setUp(){
        todoListRepository.deleteAll();
    }

    @Test
    void should_return_all_todo_list_when_find_all_given_todo_list() throws Exception {
        //given
        TodoItem todoItem1 = new TodoItem("text 1", false);
        TodoItem todoItem2 = new TodoItem("text 2", false);
        todoListRepository.save(todoItem1);
        todoListRepository.save(todoItem2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/todos"));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].text").value(todoItem1.getText()))
                .andExpect(jsonPath("$[0].done").value(todoItem1.getDone()))
                .andExpect(jsonPath("$[1].text").value(todoItem2.getText()))
                .andExpect(jsonPath("$[1].done").value(todoItem2.getDone()));

    }

    @Test
    void should_return_one_todo_list_when_find_id_given_certain_id() throws Exception{
        //given
        TodoItem todoItem = new TodoItem("text1",false);
        todoListRepository.save(todoItem);
        //when
        ResultActions resultActions = mockMvc.perform(get("/todos/" + todoItem.getId()));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value(todoItem.getText()))
                .andExpect(jsonPath("$.done").value(todoItem.getDone()));
    }

    @Test
    void should_add_one_todo_list_when_post_given_employee_info() throws Exception{
        //given
        TodoItem todoItem = new TodoItem("text",false);
        //when
        ResultActions resultActions = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoItem)));
        //then
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value(todoItem.getText()))
                .andExpect(jsonPath("$.done").value(todoItem.getDone()));
    }
}
