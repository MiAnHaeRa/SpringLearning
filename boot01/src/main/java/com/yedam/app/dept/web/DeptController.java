package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	
	private DeptService deptService;
	
	@Autowired
	public DeptController(DeptService deptService) {
		this.deptService = deptService;
	}
	
	//전체 조회
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.deptList();
		model.addAttribute("deptList", list);
		
		return "dept/list";
	}
	
	//단건 조회
	@GetMapping("deptInfo")
	public String deptInfo(Model model, DeptVO deptVO) {
		DeptVO infoVO = deptService.deptInfo(deptVO);
		model.addAttribute("deptInfo" , infoVO);
		
		return "dept/info";
	}
	
	//단건 수정 - 페이지
	@GetMapping("deptUpdate")
	public String deptUpdatePage(Model model, DeptVO deptVO) {
		DeptVO infoVO = deptService.deptInfo(deptVO);
		model.addAttribute("deptInfo" , infoVO);
		
		return "dept/update";
	}
	
	//단건 수정 - 처리
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String, Object> deptUpdateJSON(@RequestBody DeptVO deptVO) {
		return deptService.deptUpdate(deptVO);
	}
	
	//단건 등록 - 페이지
	@GetMapping("deptInsert")
	public String deptInsertPage() {
		return "dept/insert";
	}
	
	//단건 등록 - 처리
	@PostMapping("deptInsert")
	@ResponseBody
	public Map<String, Object> deptInsertJSON(@RequestBody DeptVO deptVO) {
		return deptService.deptInsert(deptVO);
	}
	
	//단건 삭제 - 처리
	@GetMapping("deptDelete")
	public String deptDeleteJSON(int departmentId) {
		deptService.deptDelete(departmentId);
		return "redirect:deptList";
	}
}
