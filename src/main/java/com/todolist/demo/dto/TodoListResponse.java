package com.todolist.demo.dto;
//TBC, no need to do right now
public class TodoListResponse {
    private int id;
    private String text;
    private boolean done;

    public TodoListResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
