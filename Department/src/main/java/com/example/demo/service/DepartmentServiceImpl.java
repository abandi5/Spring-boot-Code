package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.repository.RepositoryDepartment;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentServiceInterface {

	RepositoryDepartment repositoryDepartment;
	
	@Autowired
	public DepartmentServiceImpl(RepositoryDepartment repositoryDepartment) {
		this.repositoryDepartment = repositoryDepartment;
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Department> getDeptDetails() {
		return repositoryDepartment.findAll();
	}

	@Override
	public DepartmentDTO saveDeptDetails(Department department) {
		DepartmentDTO dep = new DepartmentDTO();
		//Optional<Department> opt = repositoryDepartment.findById(department.getId());
		boolean present = repositoryDepartment.findById(department.getId()).isPresent();
		if (present) {
			dep.setStatus("ERROR");
			dep.setMessage("Department Id already Existed..");
		} else {
			repositoryDepartment.save(department);
			dep.setStatus("OK");
			dep.setMessage("Added Successfully.");

		}
		dep.setDeptList(repositoryDepartment.findAll());
		return dep;

	}

	@Override
	public DepartmentDTO saveAllDeptDetails(List<Department> department) {
		DepartmentDTO departmentDTO = new DepartmentDTO();
		List<Integer> addedRec=new ArrayList<>();
		List<Integer> existRexords=new ArrayList<>();
		for (Department department2 : department) {
			boolean present = repositoryDepartment.findById(department2.getId()).isPresent();
			if (!present) {
				addedRec.add(department2.getId());
				repositoryDepartment.save(department2);
			}
			else {
				existRexords.add(department2.getId());
			}
		}
		departmentDTO.setStatus("OK");
		departmentDTO.setMessage("Added Departmenst : "+addedRec+ " \n Already Existed Departments : "+existRexords);
		departmentDTO.setDeptList(repositoryDepartment.findAll());
		return departmentDTO;
	}

	@Override
	public DepartmentDTO updateDepartmentDetails(Department department) {

		DepartmentDTO dto = new DepartmentDTO();
		Department department1 = repositoryDepartment.findById(department.getId()).orElse(null);
		if (null != department1) {
			department1.setId(department.getId());
			department1.setName(department.getName());
			department1.setCourse(department.getCourse());
			department1.setSalary(department.getSalary());
			repositoryDepartment.save(department1);
			dto.setMessage("Updated successfully.");
			dto.setStatus("OK");
			dto.setDeptList(repositoryDepartment.findAll());

		} else {
			dto.setMessage("unable to update record..");
			dto.setStatus("ERROR");

		}
		return dto;
	}

	@Override
	public DepartmentDTO deleteDepartmentDetailsByid(int id) {

		DepartmentDTO dto = new DepartmentDTO();

		Optional<Department> findById = repositoryDepartment.findById(id);
		if (findById.isPresent() && findById != null) {
			repositoryDepartment.delete(findById.get());
			dto.setStatus("Ok");
			dto.setMessage("record deleted successfuylly");
			dto.setDeptList(repositoryDepartment.findAll());

		} else {
			dto.setStatus("BadRequest");
			dto.setMessage("record id not found in database" + id);
		}
		return dto;

	}

	@Override
	public DepartmentDTO getDepartmentList(Integer id) {
		DepartmentDTO dto = new DepartmentDTO();
		System.out.print(id);
		Optional<Department> findById = repositoryDepartment.findById(id);
		if (findById.isPresent()) {
			Department department = findById.get();
			if (department != null) {

				dto.setStatus("OK");
				dto.setMessage("Id found successfully with given id: " + id);

				List<Department> list = new ArrayList<>();
				list.add(department);
				dto.setDeptList(list);

			}
		} else {
			dto.setStatus("BadRequest");
			dto.setMessage("Id not found" + id);
		}
		return dto;
	}

}
