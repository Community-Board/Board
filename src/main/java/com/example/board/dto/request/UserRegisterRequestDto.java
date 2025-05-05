package com.example.board.dto.request;

import com.example.board.entity.UserEntity;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterRequestDto {
    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String userPw;

    @NotBlank(message = "닉네임은 필수입니다.")
    private String userNick;

    @NotNull(message = "생년월일은 필수입니다.")
    private LocalDate userBirth;

    @Pattern(regexp = "M|F", message = "성별은 M 또는 F이어야 합니다.")
    private String userGender;

    @Pattern(regexp = "\\d{10,11}", message = "전화번호 형식이 올바르지 않습니다.")
    private String userTel;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String userEmail;
    
    public UserEntity toEntity(String encodedPw) {
        return UserEntity.builder()
                .userId(userId)
                .userPw(encodedPw)
                .userNick(userNick)
                .userBirth(userBirth)
                .userGender(userGender)
                .userTel(userTel)
                .userEmail(userEmail)
                .build();
    }
}