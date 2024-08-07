package com.yedam.app.test.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

@CrossOrigin(origins = "*")
@Controller
public class ParamController {
	// QueryString(질의문자열) : key=value&key=value&...
	// Content-Type : application/x-www-urlencode
	// Method : 상관없음
	
	// QueryString => 커맨드 객체 (어노테이션 X) : 객체
	@RequestMapping(path = "/comobj", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /comobj \n";
		result += "\t employee_id : " + empVO.getEmployeeId() + "\n";
		result += "\t last_name : " + empVO.getLastName() + "\n";
		result += "\t hire_date : " + empVO.getHireDate() + "\n";

		return result;
	}
	
	//QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path = "/reqparam", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam
		(@RequestParam Integer employeeId, 	//필수
						String lastName, 	//생략가능
		@RequestParam(name="message", defaultValue = "No message", required = true) String msg) {
		String result = "";
		result += "path : /reqparam \n";
		result += "\t employee_id : " + employeeId + "\n";
		result += "\t last_name : " + lastName + "\n";
		result += "\t message : " + msg;
		
		return result;
	}
	
	//@PathVariable : 경로에 값을 포함하는 방식, 단일 값
	// Method : 상관없음
	// Content-Type : 상관없음
	@RequestMapping("path/{id}")	//id가 누락될경우 경로를 못찾아서 404가 뜸
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		
		return result;
	}
	
	//@RequestBody : JSON 포맷 => 배열 or 객체
	// Method : POST, PUT
	// Content-Type : application/json
	@RequestMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "";
		result += "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId() + "\n";
		result += "\t last_name : " + empVO.getLastName() + "\n";
		result += "\t hire_date : " + empVO.getHireDate() + "\n";
		
		return result;
	}
	
	@RequestMapping("resbodyList")
	@ResponseBody
	public String requestBody(@RequestBody List<EmpVO> list) {
		String result = "";
		result += "path : /resbodyList \n";
		for(EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId() + "\n";
			result += "\t last_name : " + empVO.getLastName() + "\n";			
		}
		
		return result;
	}
}
