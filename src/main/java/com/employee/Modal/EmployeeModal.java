package com.employee.Modal;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;





@Entity
@Table(name = "tbl_employee")

public class EmployeeModal implements Serializable {
	public static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "empno")
	private int empNo;

	@Column(name = "empname")
	private String empName;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "salary")
	private int salary;
	
	@Column(name = "joiningdate")
	private String joiningDate;
	
	
	
	
	
	


public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

public EmployeeModal() {
		


	}

	public EmployeeModal(int id, int empNo, String empName,  int salary,String department,String joiningDate) {
		
		this.id = id;
		this.empNo = empNo;
		this.empName = empName;
		this.department = department;
		this.salary = salary;
		this.joiningDate = joiningDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
