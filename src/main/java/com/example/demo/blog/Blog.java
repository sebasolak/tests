package com.example.demo.blog;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Blog {
    public static int COUNTER = 0;
    private final List<Post> posts;
    private final List<User> users;

    public Blog() {
        posts = new ArrayList<>();
        users = new ArrayList<>();
    }

    public int adduser(User user) {
        Optional<User> userOptional = users.stream().filter(user1 -> user1.getId() == user.getId())
                .findFirst();
        if (userOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Cannot add user - user of %d already exist.", user.getId()));
        }
        users.add(user);
        return 1;
    }

    public User findUserById(int userId) {
        Optional<User> userOptional = users.stream().filter(user -> userId == user.getId())
                .findFirst();
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException(String.format("Cannot publish post - user of %d doesn't exist.", userId));
        }
        return userOptional.get();
    }

    public String addPost(int userId, int postId, String content) {
        User userById = findUserById(userId);
        Post post = new Post(postId, LocalDate.now(), userById, content);
        posts.add(post);
        return String.format("%s posted just now: %s", userById.getNick(), content);
    }


    public String commentPost(int userId, int postId, String content) {
        Comment comment = new Comment(++Blog.COUNTER, LocalDate.now(), findUserById(userId), content);
        String nick = "";
        for (Post post : posts) {
            if (post.getId() == postId) {
                post.getComments().add(comment);
                nick = findUserById(post.getUser().getId()).getNick();
                break;
            }
        }
        return String.format("%s commented %s's post: %s",
                findUserById(userId).getNick(), nick, content);
    }

    public String userEntries(int userId) {
        User userById = findUserById(userId);
        StringBuilder sb = new StringBuilder();
        sb.append(userById.getNick()).append("'s entries:\n");
        for (Post post : posts) {
            if (post.getUser().getId() == userId) {
                sb.append("(POST) ").append(post.getContent()).append("\n");
            }
            for (Comment comment : post.getComments()) {
                if (comment.getUser().getId() == userId) {
                    sb.append("(COMMENT) ").append(comment.getContent()).append("\n");
                }
            }
        }
        return sb.toString().trim();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<User> getUsers() {
        return users;
    }
}
