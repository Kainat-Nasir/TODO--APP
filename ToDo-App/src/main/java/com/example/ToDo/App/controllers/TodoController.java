package com.example.ToDo.App.controllers;

import com.example.ToDo.App.models.Todo;
import com.example.ToDo.App.repositories.TodoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
    import javax.validation.Valid;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Sort;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;



    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        Sort sortByCreatedAtDesc = Sort.by(Sort.Direction.DESC, "createdAt");
        return todoRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/todos")
    public Todo createTodo(@Valid @RequestBody Todo todo) {
        todo.setCompleted(false);
        return todoRepository.save(todo);
    }

    @GetMapping(value="/todos/{id}")
    public Optional<Todo> getTodoById(@PathVariable("id") String id) {
        return todoRepository.findById(id);
    }

    @PutMapping(value="/todos/{id}")
    public Optional<Todo> updateTodo(@PathVariable("id") String id, @Valid @RequestBody Todo todo) {
        return Optional.of(todoRepository.save(todo));
    }

    @DeleteMapping(value="/todos/{id}")
    public void deleteTodo(@PathVariable("id") String id) {
        todoRepository.deleteById(id);
    }
}