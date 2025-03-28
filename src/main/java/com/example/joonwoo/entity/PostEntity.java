package com.example.joonwoo.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostEntity {

    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    
    public void setId(int id) {
        this.id = id;
    } // 세터 추가하면 PostController에서 setId가 오류가 안나는데 없애면 오류가 남 롬복오류인지 잘 모르겠음 확인이 필요함
    // 해결 방법을 못찾아서 강제로 세터 추가
    // id에 따로 롬복 세터를 달아봤으나 오류가 사라지지 않음
    
}
