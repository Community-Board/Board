package com.example.joonwoo.dto;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String userId;
    private String userPw;

    public String getUsername() {
        return userId;
    }

    public void setUsername(String username) {
        this.userId = username;
    }

    public String getPassword() {
        return userPw;
    }

    public void setPassword(String password) {
        this.userPw = password;
    }
}

