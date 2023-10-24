package com.example.todomanagmentbackend.repositories;

import com.example.todomanagmentbackend.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
