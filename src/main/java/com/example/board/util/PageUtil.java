package com.example.board.util;

public class PageUtil {
	
	public static int calculateOffset(int page, int pageSize) {
		return (page - 1) * pageSize;
	}
}
