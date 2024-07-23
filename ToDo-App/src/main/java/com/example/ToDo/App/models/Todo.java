package com.example.ToDo.App.models;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="todos")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Data
public class Todo {
    @Id
    private String id;


    private String title;

    private Boolean completed = false;

    private Date createdAt = new Date();

    public Todo() {
        super();
    }

    public Todo(String title) {
        this.title = title;
    }

}

