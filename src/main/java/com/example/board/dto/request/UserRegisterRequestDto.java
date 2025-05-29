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
    @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하로 입력해주세요.")
    private String userId;

	@NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String userPw;

	@NotBlank(message = "닉네임은 필수입니다.")
    @Size(min = 2, max = 20, message = "닉네임은 2자 이상 20자 이하로 입력해주세요.")
    private String userNick;

	@NotNull(message = "생년월일은 필수입니다.")
    @Past(message = "생년월일은 과거 날짜여야 합니다.")
    private LocalDate userBirth;

	@NotBlank(message = "성별은 필수입니다.")
    @Pattern(regexp = "M|F", message = "성별은 'M' 또는 'F'로 입력해주세요.")
    private String userGender;

	@NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "\\d{10,11}", message = "전화번호는 숫자만 포함하며, 10자리 또는 11자리여야 합니다.")
    private String userTel;

	@NotBlank(message = "이메일은 필수입니다.")
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