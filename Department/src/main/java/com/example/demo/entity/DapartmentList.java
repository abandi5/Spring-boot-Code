package com.example.demo.entity;

import java.util.List;

public class DapartmentList {
	private List<Department> deptLust;

	public List<Department> getDeptLust() {
		return deptLust;
	}

	public void setDeptLust(List<Department> deptLust) {
		this.deptLust = deptLust;
	}

	@Override
	public String toString() {
		return "DapartmentList [deptLust=" + deptLust + "]";
	}
	

}
