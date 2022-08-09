package com.example.demo.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.repository.RepositoryDepartment;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

	@InjectMocks
	private DepartmentServiceImpl departmentServiceImpl;

	@Mock
	private RepositoryDepartment repositoryDepartment;
	
	
	@Mock
	Department dept;

	@Test
	void testGetDeptDetails() {
		
		List<Department> list =  new ArrayList<>();
		Department department = new Department();
		department.setId(1);
		department.setName("Arun");
		department.setCourse("java");
		department.setSalary(30000.0);
		list.add(department);
		when(repositoryDepartment.findAll()).thenReturn(list);
		List<Department> response = departmentServiceImpl.getDeptDetails();
		assertNotNull(response);
		assertTrue(response.size()>0);
		
	}

	@Test
	void testSaveDeptDetails_Already_Exist() {
		
		List<Department> list = new ArrayList<>();
		Department department1 = new Department();
		department1.setId(1);
		list.add(department1);
		
		when(repositoryDepartment.findById(Mockito.any())).thenReturn(Optional.of(dept));
		when(repositoryDepartment.findAll()).thenReturn(list);
		DepartmentDTO dto = departmentServiceImpl.saveDeptDetails(new Department());
		assertNotNull(dto);
		assertTrue(dto.getStatus().equals("ERROR"));
		assertTrue(dto.getDeptList().size()>0);
	}
	
	@Test
	void testSaveDeptDetails() {
		
		List<Department> list = new ArrayList<>();
		Department department1 = new Department();
		department1.setId(1);
		list.add(department1);
		when(repositoryDepartment.findAll()).thenReturn(list);
		DepartmentDTO dto = departmentServiceImpl.saveDeptDetails(new Department());
		assertNotNull(dto);
		assertTrue(dto.getStatus().equals("OK"));
		assertTrue(dto.getDeptList().size()>0);
	}
	
	@Test
	void testSaveAllDeptDetails() {
		
		List<Department> list = new ArrayList<>();
		Department department1 = new Department();
		Department department2 = new Department();
		department1.setId(1);
		department2.setId(2);
		list.add(department1);
		list.add(department2);
		when(repositoryDepartment.findById(1)).thenReturn(Optional.of(dept));
		DepartmentDTO dto = departmentServiceImpl.saveAllDeptDetails(list);
		assertNotNull(dto);
		assertTrue(dto.getStatus().equals("OK"));
	}

	@Test
	void testUpdateDepartmentDetails() {
		
		when(repositoryDepartment.findById(Mockito.any())).thenReturn(Optional.of(dept));
		DepartmentDTO dto = departmentServiceImpl.updateDepartmentDetails(new Department());
		assertNotNull(dto);
		assertTrue(dto.getStatus().equals("OK"));
		
	}

	@Test
	void testDeleteDepartmentDetailsByid() {
		
		when(repositoryDepartment.findById(101)).thenReturn(Optional.of(dept));
		DepartmentDTO dto = departmentServiceImpl.deleteDepartmentDetailsByid(101);
		assertNotNull(dto);
		assertTrue(dto.getStatus().equals("Ok"));
	}

	@Test
	void testGetDepartmentList() {
		when(repositoryDepartment.findById(101)).thenReturn(Optional.of(dept));
		DepartmentDTO dto = departmentServiceImpl.getDepartmentList(101);
		assertNotNull(dto);
		assertTrue(dto.getStatus().equals("OK"));
	}

}
