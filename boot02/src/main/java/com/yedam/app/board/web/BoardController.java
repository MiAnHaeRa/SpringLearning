package com.yedam.app.board.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	private BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	// 전체조회 : URI - boardList / RETURN - board/boardList
	@GetMapping("boardList")
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.boardList());
		return "board/boardList";
	}

	
	// 단건조회 : URI - boardInfo / PARAMETER - BoardVO(QueryString)
	//          RETURN - board/boardInfo
	@GetMapping("boardInfo")
	public String boardInfo(Model model, BoardVO boardVO) {
		model.addAttribute("board", boardService.boardInfo(boardVO));
		
		return "board/boardInfo";
	}

	
	// 등록 - 페이지 : URI - boardInsert / RETURN - board/boardInsert
	@GetMapping("boardInsert")
	public String boardInsertPage() {
		return "board/boardInsert";
	}
	
	// 등록 - 처리 : URI - boardInsert / PARAMETER - BoardVO(QueryString)
	//             RETURN - 단건조회 다시 호출
	@PostMapping("boardInsert")		//일반적으로 <form/> 활용 => QueryString
	public String boardInsertAJAXQuery(BoardVO boardVO) {
		int bno = boardService.insertBoard(boardVO);
		
		return "redirect:boardInfo?bno="+bno;
	}
	
	// 수정 - 페이지 : URI - boardUpdate / PARAMETER - BoardVO(QueryString)
	//               RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdatePage(Model model, BoardVO boardVO) {
		model.addAttribute("board", boardService.boardInfo(boardVO));
		
		return "board/boardUpdate";
	}
	
	// 수정 - 처리 : URI - boardUpdate / PARAMETER - BoardVO(JSON)
	//             RETURN - 수정결과 데이터(Map)
	@PostMapping("boardUpdate")
	@ResponseBody	//=> AJAX (데이터 리턴)
	public Map<String, Object> boardUpdateAJAXJSON(@RequestBody BoardVO boardVO ) {
		return boardService.updateBoard(boardVO);
	}

	
	// 삭제 - 처리 : URI - boardDelete / PARAMETER - Integer => QueryString
	//             RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(Integer no) {
		boardService.deleteBoard(no);
		
		return "redirect:boardList";
	}
}
