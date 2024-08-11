package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	//전체조회
	public List<DeptVO> deptList();
	//단건조회
	public DeptVO deptInfo(DeptVO deptVO);
	//단건등록
	public Map<String, Object> deptInsert(DeptVO deptVO);
	//단건수정
	public Map<String, Object> deptUpdate(DeptVO deptVO);
	//단건삭제
	public int deptDelete(int deptId);
}
