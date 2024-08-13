package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;


@SpringBootTest
class Boot02ApplicationTests {
	
	@Autowired
	BoardMapper boardMapper;

	@Test
	void getInfo() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(100);
		BoardVO infoVO = boardMapper.selectBoardInfo(boardVO);
		
		assertEquals("First Test", infoVO.getTitle());
	}
	
	@Test
	void getAll() {
		List<BoardVO> list = boardMapper.selectBoardAll();
		assertTrue(!list.isEmpty());
	}

	@Test
	void insetInfo() {
		// bno, title, contents, writer, regdate, image
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(101);
		boardVO.setTitle("Second");
		boardVO.setContents("테스트");
		boardVO.setWriter("테스터");
		boardVO.setRegdate(new Date());
		boardVO.setImage(null);
		
		int result = boardMapper.insertBoardInfo(boardVO);
		
		assertEquals(1, result);
	}
	
	@Test
	void updateInfo() {
		// bno, title, contents, writer, regdate, image
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(101);
		boardVO.setTitle("update Title");
		boardVO.setContents("테스트");
		boardVO.setWriter("테스터");
		boardVO.setUpdatedate(new Date());
		boardVO.setImage(null);
		
		int result = boardMapper.updateBoardInfo(boardVO);
		
		assertEquals(1, result);
	}
	
	@Test
	void deleteInfo() {
		int result = boardMapper.deleteBoardInfo(101);
		
		assertEquals(1, result);
	}

}
