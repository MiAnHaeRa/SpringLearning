package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService{
	
	private DeptMapper deptMapper;
	
	@Autowired
	public DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}

	@Override
	public List<DeptVO> deptList() {
		return deptMapper.deptList();
	}

	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.deptInfo(deptVO);
	}

	@Override
	public Map<String, Object> deptInsert(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = false;
		
		int result = deptMapper.deptInsert(deptVO);
		
		if(result == 1) {
			isSuccess = true;
		}
		
		map.put("result", isSuccess);
		map.put("target", deptVO);
		
		return map;
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isSuccess = false;
		
		int result = deptMapper.deptUpdate(deptVO.getDepartmentId(), deptVO);
		
		if(result == 1) {
			isSuccess = true;
		}
		
		map.put("result", isSuccess);
		map.put("target", deptVO);
		
		return map;
	}

	@Override
	public int deptDelete(int deptId) {
		return deptMapper.deptDelete(deptId);
	}
	
}
