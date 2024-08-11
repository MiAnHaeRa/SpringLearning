package com.yedam.app.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	// 전체조회
	public List<DeptVO> deptList();
	// 단건조회
	public DeptVO deptInfo(DeptVO deptVO);
	// 단건등록
	public int deptInsert(DeptVO deptVO);
	// 단건수정
	public int deptUpdate(@Param("id") int deptId, @Param("dept") DeptVO deptVO);
	// 단건삭제
	public int deptDelete(int deptId);
}
