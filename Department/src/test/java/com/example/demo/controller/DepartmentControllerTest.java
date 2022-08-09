package com.example.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.DapartmentList;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentServiceInterface;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

	@InjectMocks
	private DepartmentController departmentController;

	@Mock
	private DepartmentServiceInterface departmentServiceInterface;

	@Test
	public void test_saveDeptDetails() throws Exception {
		Department department = new Department();
		department.setId(1);
		when(departmentServiceInterface.saveDeptDetails(department)).thenReturn(new DepartmentDTO());
		DepartmentDTO dto = departmentController.saveDeptDetails(department);
		assertNotNull(dto);
	}

	@Test
	public void test_getDeptDetails() throws Exception {
		List<Department> departments = new ArrayList<Department>();
		departments.add(new Department(1, "Arun", 1000, "SE"));
		when(departmentServiceInterface.getDeptDetails()).thenReturn(departments);
		List<Department> response = departmentController.getDeptDetails();
		assertNotNull(response);
		assertTrue(response.get(0).getId() == 1);
	}

	@Test
	public void test_saveAllDeptDetails() throws Exception {
		DapartmentList departmentlist = new DapartmentList();
		Department department = new Department(1, "RTEST", 1000, "TEST");
		List<Department> arrayList = new ArrayList<Department>();
		arrayList.add(department);
		departmentlist.setDeptLust(arrayList);
		when(departmentServiceInterface.saveAllDeptDetails(Mockito.any()))
				.thenReturn(new DepartmentDTO());
		DepartmentDTO dto = departmentController.saveAllDeptDetails(new DapartmentList());
		assertNotNull(dto);
	}

	@Test
	public void test_updateDepartmentDetails() throws Exception {
		Department department = new Department();
		department.setId(1);
		department.setName("Arun");
		department.setCourse("ECE");
		department.setSalary(89000);
		when(departmentServiceInterface.updateDepartmentDetails(department)).thenReturn(new DepartmentDTO());
		DepartmentDTO dto = departmentController.updateDepartmentDetails(department);
		assertNotNull(dto);
	}

	@Test
	public void test_deleteDepartmentDetailsByid() throws Exception {
		when(departmentServiceInterface.deleteDepartmentDetailsByid(1)).thenReturn(new DepartmentDTO());
		DepartmentDTO dto = departmentController.deleteDepartmentDetailsByid(1);
		assertNotNull(dto);
	}

	@Test
	public void test_getDepartmentList() throws Exception {
		Department department = new Department();
		department.setId(100);
		when(departmentServiceInterface.getDepartmentList(100)).thenReturn(new DepartmentDTO());
		DepartmentDTO dto = departmentController.getDepartmentList(100);
		assertNotNull(dto);

	}
	
	@Test
	public void saveAllDeptDetailsDummy() {
		when(departmentServiceInterface.saveAllDeptDetails(Mockito.any())).thenReturn(new DepartmentDTO());
		String response = departmentController.saveAllDeptDetailsDummy();
		assertNotNull(response);
		assertTrue(response.equals("success"));
	}
}
