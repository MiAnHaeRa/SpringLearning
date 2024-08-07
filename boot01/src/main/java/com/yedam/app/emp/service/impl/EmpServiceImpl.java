package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor
public class EmpServiceImpl implements EmpService{
	
	private EmpMapper empMapper;
	
	@Autowired
	EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}

	@Override
	public List<EmpVO> empList() {
		// TODO Auto-generated method stub
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		// TODO Auto-generated method stub
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		
		return result == 1 ? empVO.getEmployeeId() : -1;
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccessed = false;
		
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		return map;
	}

	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("employeeId", empId);
		}
		
		return map;
	}
	
}
