package com.example.board.vo;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CrudVO {
	private String id;
	private String title;
	private String content;
	private LocalDateTime date;
}
