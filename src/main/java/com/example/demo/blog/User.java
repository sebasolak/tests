package com.example.demo.blog;

public class User {

    private final int id;
    private final String name;
    private final String surname;
    private final String nick;

    public User(int id, String name, String surname, String nick) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nick = nick;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNick() {
        return nick;
    }
}
