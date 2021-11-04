package com.todolist.demo.mapper;
//TBC, no need to do right now

import com.todolist.demo.dto.TodoListRequest;
import com.todolist.demo.dto.TodoListResponse;
import com.todolist.demo.entity.TodoItem;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoListMapper {
    public TodoItem toEntity(TodoListRequest todoListRequest){
        TodoItem todoItem = new TodoItem();
        BeanUtils.copyProperties(todoListRequest, todoItem);

        return todoItem;
    }

    public TodoListResponse toResponse(TodoItem todoItem){
        TodoListResponse todoListResponse = new TodoListResponse();

        BeanUtils.copyProperties(todoItem, todoListResponse);

        return todoListResponse;
    }
}
