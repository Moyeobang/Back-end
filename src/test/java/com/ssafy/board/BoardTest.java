package com.ssafy.board;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;

public class BoardTest extends SpringbootRestapiApplicationTests {
	private final Logger logger = LoggerFactory.getLogger(CategoryTest.class);

	@Autowired
	private BoardService boardService;
	
	// TODO : Category 생성 sql문 찾기. 없어서 테스트 불가.
	@Test
	public void getBoard() throws Exception {
		try {
		BoardDto boardDto = boardService.getArticle(8);
		assertNotNull(boardDto);
		System.out.println(boardDto);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}