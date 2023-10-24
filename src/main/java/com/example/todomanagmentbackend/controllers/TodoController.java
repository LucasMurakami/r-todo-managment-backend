package com.example.todomanagmentbackend.controllers;

import com.example.todomanagmentbackend.DTO.TodoDTO;
import com.example.todomanagmentbackend.services.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

    private TodoService todoService;

    @PostMapping()
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO savedTodo = todoService.addTodo(todoDTO);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable("id") Long todoId) {
        TodoDTO todoDTO = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDTO, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todosDTO = todoService.getAllTodos();
        return new ResponseEntity<>(todosDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") Long todoId, @RequestBody TodoDTO todoDTO) {
        TodoDTO updatedTodo = todoService.updateTodo(todoId, todoDTO);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Deleted Sucessfully! ");
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDTO> completeTodo(@PathVariable("id") Long id) {
        TodoDTO updatedTodo = todoService.completedTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }

    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<TodoDTO> incompleteTodo(@PathVariable("id") Long id) {
        TodoDTO updatedTodo = todoService.incompletedTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }
}
