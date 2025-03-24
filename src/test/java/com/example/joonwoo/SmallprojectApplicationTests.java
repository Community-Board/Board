package com.example.joonwoo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.SmallprojectApplication;

@SpringBootTest(classes = SmallprojectApplication.class)  // ✅ 애플리케이션 클래스를 명시적으로 지정
public class SmallprojectApplicationTests {
    @Test
    void contextLoads() {
        // 테스트 실행 (단순 컨텍스트 로딩 테스트)
    }
}
