package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.DapartmentList;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentServiceInterface;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j;


@RestController
@Log4j
public class DepartmentController {

	@Autowired
	DepartmentServiceInterface departmentServiceInterface;
	
	private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(DepartmentController.class.getName());
	
	@GetMapping("/getDeptDetails")
	@Operation(summary = "To get Department Details", responses = {
			@ApiResponse(responseCode = "200", description = "Success"),
			@ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found")
	})
	public List<Department> getDeptDetails(){
		log.info("Method to run the getDeptDetails");
		return departmentServiceInterface.getDeptDetails();
	}
	
	@PostMapping("/saveDeptDetails")
	public DepartmentDTO saveDeptDetails(@RequestBody Department department){
		log.info("department:-"+department);
		return departmentServiceInterface.saveDeptDetails(department);
	
		
	}
	
	@PostMapping("/saveAllDeptDetails")
	public DepartmentDTO saveAllDeptDetails(@RequestBody DapartmentList department)
	{
		return departmentServiceInterface.saveAllDeptDetails(department.getDeptLust());
	}
	
	@PutMapping("/updateDepartmentDetails")
	public DepartmentDTO updateDepartmentDetails(@RequestBody Department department)
	{
		return departmentServiceInterface.updateDepartmentDetails(department);
	}
	
//	@GetMapping("/getDepDetailsByID{id}")
//	 public void getDepDetailsByID(int id)
//	 {
//		departmentServiceInterface.getDepDetailsByID(id);
//	 }
	@DeleteMapping("/deleteDepartmentDetailsByid/{id}")
	public DepartmentDTO deleteDepartmentDetailsByid(@PathVariable int id)
	{
		return departmentServiceInterface.deleteDepartmentDetailsByid(id);
	}
	
	@GetMapping("/getDepartmentList/{id}")
	 public DepartmentDTO getDepartmentList(@PathVariable Integer id)
	 {
		return departmentServiceInterface.getDepartmentList(id);
	 }
	
	@GetMapping("/createDummyData")
	public String saveAllDeptDetailsDummy()
	{
		DapartmentList department = new DapartmentList();
		List<Department> list = new ArrayList<Department>();
		Department dep = new Department(100, "Dummy 1", 100.0d, "ECE");
		Department dep1 = new Department(101, "Dummy 2", 500.0d, "CSE");
		list.add(dep);list.add(dep1);
		department.setDeptLust(list);
		departmentServiceInterface.saveAllDeptDetails(department.getDeptLust());
		return "success";
	}

}
