package com.example.joonwoo.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserEntity {
    private Long userNo;
    private String userId;
    private String userPw;
    private String userNick;
    private LocalDate userBirth;
    private String userGender;
    private String userTel;
    private String userEmail;
    private String userRole;
    private boolean isDeleted;
}