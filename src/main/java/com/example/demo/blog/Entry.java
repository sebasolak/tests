package com.example.demo.blog;

import java.time.LocalDate;

public abstract class Entry {

    private final int id;
    private final LocalDate date;
    private final User user;
    private final String content;

    public Entry(int id, LocalDate date, User user, String content) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
