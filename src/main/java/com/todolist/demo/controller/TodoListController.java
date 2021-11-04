package com.todolist.demo.controller;


import com.todolist.demo.dto.TodoListRequest;
import com.todolist.demo.dto.TodoListResponse;
import com.todolist.demo.entity.TodoItem;
import com.todolist.demo.mapper.TodoListMapper;
import com.todolist.demo.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
//@CrossOrigin(origins = "http://localhost:3000")
public class TodoListController {
    private final TodoListService todoListService;
    private final TodoListMapper todoListMapper;

    public TodoListController(TodoListService todoListService, TodoListMapper todoListMapper) {
        this.todoListService = todoListService;
        this.todoListMapper = todoListMapper;
    }

    @GetMapping
    public List<TodoListResponse> findAllTodoList(){
        return this.todoListService.findAll()
                .stream()
                .map(todoListMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TodoListResponse findById(@PathVariable Integer id){
        return this.todoListMapper.toResponse(todoListService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoListResponse createTodoList(@RequestBody TodoItem todoItem){
        return this.todoListMapper.toResponse(todoListService.createTodoList(todoItem));
    }

    @PutMapping("/{id}")
    public TodoListResponse updateTodoItem(@PathVariable Integer id,
                                           @RequestBody TodoListRequest todoListRequest){
        return this.todoListMapper
                .toResponse(todoListService
                .updateTodoItem(id,todoListMapper.toEntity(todoListRequest)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        this.todoListService.deleteById(id);
    }
}
