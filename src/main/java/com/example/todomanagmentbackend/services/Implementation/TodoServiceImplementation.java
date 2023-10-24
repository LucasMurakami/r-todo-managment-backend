package com.example.todomanagmentbackend.services.Implementation;

import com.example.todomanagmentbackend.DTO.TodoDTO;
import com.example.todomanagmentbackend.entities.Todo;
import com.example.todomanagmentbackend.exceptions.ResourceNotFoundException;
import com.example.todomanagmentbackend.repositories.TodoRepository;
import com.example.todomanagmentbackend.services.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImplementation implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDTO addTodo(TodoDTO todoDTO) {
        //Convert TodoDTO into Todo Jpa entity
        Todo todo = modelMapper.map(todoDTO, Todo.class);

        //Todo Jpa entity
        Todo savedTodo = todoRepository.save(todo);

        //Convert saved Todo Jpa Entity object into TodoDTO object
        return modelMapper.map(savedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo not found with id: " + id));
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map( (todo) -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo not found with id: " + id));

        todoRepository.deleteById(id);
    }

    @Override
    public TodoDTO completedTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

    @Override
    public TodoDTO incompletedTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDTO.class);
    }

}
