package com.example.demo.blog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Post  extends Entry{


    private final List<Comment> comments;

    public Post(int id, LocalDate date, User user, String content) {
        super(id, date, user, content);
        this.comments = new ArrayList<>();
    }
    public List<Comment> getComments() {
        return comments;
    }


}
