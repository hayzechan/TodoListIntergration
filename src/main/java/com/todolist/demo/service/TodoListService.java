package com.todolist.demo.service;

import com.todolist.demo.entity.TodoItem;
import com.todolist.demo.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private final TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoItem> findAll(){
        return this.todoListRepository.findAll();
        //select *
    }

    public TodoItem findById(Integer id){
        return this.todoListRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public TodoItem createTodoList(TodoItem todoItem){
        return this.todoListRepository.save(todoItem);
    }

    public TodoItem updateTodoItem(Integer id, TodoItem updateItem){
        TodoItem todoItem = this.todoListRepository.findById(id).orElseThrow(RuntimeException::new);
        if(updateItem.getText() != null){
            todoItem.setText(updateItem.getText());
        }
        if(updateItem.getDone() != null){
            todoItem.setDone(updateItem.getDone());
        }
        return this.todoListRepository.save(todoItem);
    }
    public void deleteById(Integer id){
        this.todoListRepository.deleteById(id);
    }

}
