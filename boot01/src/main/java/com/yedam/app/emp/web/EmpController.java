package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller		// == roter : 사용자의 요청(end point)와 그에 대한 처리
				// 			: URL + Method => Service => View
public class EmpController {
	// 해당 컨트롤러에서 제공하는 서비스
	private EmpService empService;
	
	@Autowired
	public EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	// GET : 조회, 빈페이지
	// POST : 데이터 조작 (등록, 수정, 삭제)
	
	//전체조회 : GET
	@GetMapping("empList")	//Model = Request + Response
	public String empList(Model model) {
		// 1) 해당기능 수행	=> Service
		List<EmpVO> list = empService.empList();
		
		// 2) 클라리언트에게 전달할 데이터 담기
		model.addAttribute("emps", list);
		
		// 3) 데이터를 출력할 페이지 결정
		return "emp/list";
		// classpath:/templates/	emp/list	.html
		//			prefix			return		suffix
	}
	
	//단건조회 : Get => QueryString
	@GetMapping("empInfo")
	public String empInfo(Model model, EmpVO empVO) {
		EmpVO findVO = empService.empInfo(empVO);
		
		model.addAttribute("emp", findVO);
		
		return "emp/info";
//		return "redirect:empList";
	}
	
	//등록 - 페이지 : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	//등록 - 처리 : POST	=> form태그를 통한 submit
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		
		String url = null;
		if( eid > -1 ) {
			//정상적으로 등록 되었을 경우
			url = "redirect:empInfo?employeeId=" + eid;
		} else {
			//등록되지 않은 경우
			url = "redirect:empList";
		}
		
		return url;
	}
	
	//수정 - 페이지 : GET <=> 단건조회
	@GetMapping("empUpdate")
	public String empUpdateForm(Model model, EmpVO empVO) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
	}
	
	//수정 - 처리 : AJAX => QueryString
	@PostMapping("empUpdate")
	@ResponseBody	//AJAX
	public Map<String, Object> empUpdateAJAXQueryString(EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	//수정 - 처리 : AJAX => JSON (@RequestBody)
//	@PostMapping("empUpdate")
	@ResponseBody	//AJAX
	public Map<String, Object> empUpdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	//삭제 - 처리 : GET
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList";
	}
	
}
