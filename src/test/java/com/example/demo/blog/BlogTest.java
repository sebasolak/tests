package com.example.demo.blog;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;


class BlogTest {

    private Blog underTest;

    @BeforeEach
    void setUp() {
        underTest = new Blog();
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(1, new User(1, "Tony", "Montana", "scarface")),
                Arguments.of(2, new User(2, "Joe", "Jones", "joey95")),
                Arguments.of(3, new User(3, "Anna", "Smith", "xxanniexx"))
        );
    }

    @Test
    void itShouldAddNewUser() {
        //given
        User user = new User(1, "Tony", "Montana", "scarface95");
        //then
        int addUser = underTest.adduser(user);
        //when
        assertThat(addUser).isOne();
        assertThat(underTest.getUsers()).hasSize(1);
        assertThat(underTest.getUsers()).extracting("id").contains(1);
        assertThat(underTest.getUsers()).extracting("name").contains("Tony");
        assertThat(underTest.getUsers()).extracting("surname").contains("Montana");
        assertThat(underTest.getUsers()).extracting("nick").contains("scarface95");
    }

    @Test
    void itShouldThrowExceptionWhenUserAlreadyExsists() {
        //given
        User user = new User(1, "Tony", "Montana", "scarface95");
        //then
        int addUser = underTest.adduser(user);
        User user2 = new User(1, "Tony", "Montana", "scarface95");
        //when
        assertThatThrownBy(() -> underTest.adduser(user2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Cannot add user - user of %d already exist.", user2.getId()));
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void idShouldFetchUser(int id, User expected) {
        //then
        underTest.adduser(expected);
        //when
        assertThat(underTest.findUserById(id))
                .isEqualTo(expected);
    }

    @Test
    void itShouldThrowExceptionWhenUserIsNotPresent() {
        assertThatThrownBy(() -> underTest.findUserById(101))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Cannot publish post - user of %d doesn't exist.", 101));
    }

    @Test
    void itShouldAddPost() {
        //given
        User user = new User(1, "Tony", "Montana", "scarface95");
        Post post = new Post(11, LocalDate.now(), user, "Test post.");
        //then
        underTest.adduser(user);
        String addingPost = underTest.addPost(1, 11, "Test post.");
        //when
        assertThat(addingPost).isEqualTo(String.format("%s posted just now: %s", user.getNick(), post.getContent()));
        assertThat(underTest.getPosts()).hasSize(1);
        assertThat(underTest.getPosts()).extracting("id").contains(post.getId());
        assertThat(underTest.getPosts().get(0).getDate()).isInstanceOf(LocalDate.class);
        assertThat(underTest.getPosts()).extracting("user").contains(user);
        assertThat(underTest.getPosts()).extracting("content").contains(post.getContent());
    }

    @Test
    void itShouldCommentPost() {
        //given
        User user = new User(1, "Tony", "Montana", "scarface95");
        User user2 = new User(2, "Anna", "Smith", "yyAnniEyy");
        Post post = new Post(11, LocalDate.now(), user, "Test post.");
        //when
        underTest.adduser(user);
        underTest.adduser(user2);
        underTest.addPost(1, 11, "Test post.");
        //then
        assertThat(underTest.commentPost(user2.getId(), post.getId(), "Test comment."))
                .isEqualTo("%s commented %s's post: %s",
                        underTest.findUserById(user2.getId()).getNick(),
                        underTest.findUserById(user.getId()).getNick(),
                        "Test comment.");
    }

    @Test
    void itShouldFetchUserEntries() {
        //given
        User user = new User(1, "Tony", "Montana", "scarface95");
        User user2 = new User(2, "Anna", "Smith", "yyAnniEyy");
        User user3 = new User(3, "Joe", "Jones", "joe_dev");
        //when
        underTest.adduser(user);
        underTest.adduser(user2);
        underTest.adduser(user3);

        underTest.addPost(1, 11, "Lorem ipsum dolor sit amet.");
        underTest.addPost(2, 22, "Fusce quis mi ex. Ut tortor leo, ornare accumsan leo vitae, blandit scelerisque lorem.");
        underTest.addPost(3, 33, "Praesent sed purus id diam tempus auctor.");

        underTest.commentPost(1, 22, "Sed finibus fringilla laoreet.");
        underTest.commentPost(1, 33, "Aliquam erat volutpat.");
        //then
        String expected = "scarface95's entries:\n" +
                "(POST) Lorem ipsum dolor sit amet.\n" +
                "(COMMENT) Sed finibus fringilla laoreet.\n" +
                "(COMMENT) Aliquam erat volutpat.";
        assertThat(underTest.userEntries(1))
                .isEqualTo(expected);

    }
}