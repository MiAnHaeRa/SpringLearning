package com.yedam.app.board.mapper;

import java.util.List;

import com.yedam.app.board.service.BoardVO;

public interface BoardMapper {
	//전체조회
	public List<BoardVO> selectBoardAll();
	//단건조회 : 조건 - bno
	public BoardVO selectBoardInfo(BoardVO boardVO);
	//단건등록 : 대상 - bno, title, contents, writer, regdate, image
	public int insertBoardInfo(BoardVO boardVO);
	//단건수정 : 대상 - bno, title, contents, writer, updatedate, image
	public int updateBoardInfo(BoardVO boardVO);
	//단건삭제 : 조건 - bno
	public int deleteBoardInfo(int boardNO);
}
