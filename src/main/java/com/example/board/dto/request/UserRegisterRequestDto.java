package com.example.board.dto.request;

import com.example.board.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterRequestDto {
    private String userId;
    private String userPw;
    private String userNick;
    private LocalDate userBirth;
    private String userGender;
    private String userTel;
    private String userEmail;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .userPw(userPw)
                .userNick(userNick)
                .userBirth(userBirth)
                .userGender(userGender)
                .userTel(userTel)
                .userEmail(userEmail)
                .build();
    }
}
