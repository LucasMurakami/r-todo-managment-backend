package com.example.todomanagmentbackend.services;

import com.example.todomanagmentbackend.DTO.TodoDTO;

import java.util.List;

public interface TodoService {

    TodoDTO addTodo(TodoDTO todoDTO);
    TodoDTO getTodo(Long id);
    List<TodoDTO> getAllTodos();
    TodoDTO updateTodo(Long id, TodoDTO todoDTO);
    void deleteTodo(Long id);
    TodoDTO completedTodo(Long id);
    TodoDTO incompletedTodo(Long id);
}
