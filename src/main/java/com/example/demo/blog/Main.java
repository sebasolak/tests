package com.example.demo.blog;

public class Main {
    public static void main(String[] args) {
        Blog blog = new Blog();

        User user = new User(1, "Tony", "Montana", "scarface95");
        User user2 = new User(2, "Anna", "Smith", "yyAnniEyy");
        User user3 = new User(3, "Joe", "Jones", "joe_dev");

        blog.adduser(user);
        blog.adduser(user2);
        blog.adduser(user3);

        System.out.println(blog.addPost(1, 11, "Lorem ipsum dolor sit amet."));
        System.out.println(blog.addPost(2, 22, "Fusce quis mi ex. Ut tortor leo, ornare accumsan leo vitae, blandit scelerisque lorem."));
        System.out.println(blog.addPost(3, 33, "Praesent sed purus id diam tempus auctor."));

        System.out.println();

        System.out.println(blog.commentPost(3, 11, "Nam ornare dolor dolor."));
        System.out.println(blog.commentPost(1, 22, "Sed finibus fringilla laoreet."));
        System.out.println(blog.commentPost(2, 22, "Fusce quis mi ex."));
        System.out.println(blog.commentPost(1, 33, "Aliquam erat volutpat."));
        System.out.println(blog.commentPost(2, 33, "Nunc tincidunt vulputate tincidunt."));

        System.out.println();

        System.out.println(blog.userEntries(1));
    }
}
