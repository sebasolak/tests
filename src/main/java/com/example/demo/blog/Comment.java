package com.example.demo.blog;

import java.time.LocalDate;

public class Comment extends Entry {

    public Comment(int id, LocalDate date, User user, String content) {
        super(id, date, user, content);
    }
}
