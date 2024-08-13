package com.yedam.app.board.service;

import java.util.List;
import java.util.Map;

public interface BoardService {
		//전체조회
		public List<BoardVO> boardList();
		//단건조회 : 조건 - bno
		public BoardVO boardInfo(BoardVO boardVO);
		//단건등록 : 대상 - bno, title, contents, writer, regdate, image
		public int insertBoard(BoardVO boardVO);
		//단건수정 : 대상 - bno, title, contents, writer, updatedate, image
		public Map<String, Object> updateBoard(BoardVO boardVO);
		//단건삭제 : 조건 - bno
		public int deleteBoard(int boardNO);
}
