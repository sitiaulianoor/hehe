package com.example.aulia.aulia_1202151364_studycase5;

/**
 * Created by Aulia on 25/03/2018.
 */
public class ToDoModel {
    String nameTodo, description, priority;

    //method setter getter

    public String getNameTodo() {

        return nameTodo;
    }

    public void setNameTodo(String name) {

        this.nameTodo = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {

        this.priority = priority;
    }

    public ToDoModel(String nameTodo, String description, String priority) {
        this.nameTodo = nameTodo;
        this.description = description;
        this.priority = priority;
    }
}
