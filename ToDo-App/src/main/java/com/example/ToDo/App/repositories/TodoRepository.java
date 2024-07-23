package com.example.ToDo.App.repositories;

import com.example.ToDo.App.models.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface TodoRepository extends MongoRepository<Todo, String> {
}
