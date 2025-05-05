package com.example.board.entity;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
