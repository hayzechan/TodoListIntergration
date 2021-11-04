package com.todolist.demo.dto;
//TBC, no need to do right now
public class TodoListRequest {
    private String text;
    private boolean done;

    public TodoListRequest() {
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
