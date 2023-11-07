package com.example.demo.dto;

public class TestDTO {
    String user_id;
    String password;
    String name;
    String nickname;
    String email;
    String git_address;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGit_address() {
        return git_address;
    }

    public void setGit_address(String gitAddress) {
        this.git_address = gitAddress;
    }
}