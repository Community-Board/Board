package com.example.joonwoo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("게시판 API 문서")
                        .version("1.0")
                        .description("게시판 API의 Swagger"));
    }
}
