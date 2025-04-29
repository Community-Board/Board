package com.example.board.dto.request;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String userId;
    private String userPw;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String username) {
        this.userId = username;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String password) {
        this.userPw = password;
    }
}

