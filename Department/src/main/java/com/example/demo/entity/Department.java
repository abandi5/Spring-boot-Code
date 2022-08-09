package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Department_table")
public class Department {

	@Id
	private int id;
	private String name;
	private double salary;
	private String course;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	
public Department()
{
	
}
	public Department(int id, String name, double salary, String course) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.course = course;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", salary=" + salary + ", course=" + course + "]";
	}
	
	
}
