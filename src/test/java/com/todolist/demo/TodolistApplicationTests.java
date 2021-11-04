package com.todolist.demo;

import com.todolist.demo.entity.TodoItem;
import com.todolist.demo.repository.TodoListRepository;
import com.todolist.demo.service.TodoListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Optional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TodolistApplicationTests {

	@Mock
	TodoListRepository todoListRepository;

	@InjectMocks
	TodoListService todoListService;

	@Test
	void should_return_all_todo_list_when_find_all_given_2_todo_items() {
		//given
		List<TodoItem> todoItems = Arrays.asList(
				new TodoItem("text1",false),
				new TodoItem("text2",true)
		);
		when(todoListRepository.findAll()).thenReturn(todoItems);
		//when
		List<TodoItem> actual = todoListService.findAll();
		//then
		assertEquals(todoItems, actual);
	}

	@Test
	void should_return_1_todo_item_when_find_specific_todo_item_given_todo_id(){
		//given
		TodoItem todoItem = new TodoItem("text1",false);
		when(todoListRepository.findById(1)).thenReturn(Optional.of(todoItem));
		//when
		TodoItem actual = todoListService.findById(1);
		//then
		assertEquals(todoItem, actual);
	}

	@Test
	void should_update_todo_item_when_update_todo_item_given_new_todo_item(){
		//given
		TodoItem updateTodoItem = new TodoItem("new text", true);
		updateTodoItem.setId(1);
		when(todoListRepository.findById(anyInt())).thenReturn(Optional.of(updateTodoItem));
		when(todoListRepository.save(any(TodoItem.class))).thenReturn(updateTodoItem);
		//when
		todoListService.updateTodoItem(updateTodoItem.getId(),updateTodoItem);
		//then
		assertEquals(1, updateTodoItem.getId());
		assertEquals("new text", updateTodoItem.getText());
		assertEquals(true, updateTodoItem.getDone());
	}

	@Test
	void should_delete_todo_item_when_delete_todo_item_given_todo_id(){
		//given
		doNothing().when(todoListRepository).deleteById(1);
		//when
		todoListService.deleteById(1);
		//then
		verify(todoListRepository, times(1)).deleteById(1);
	}

}
