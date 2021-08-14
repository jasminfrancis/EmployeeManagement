package com.employee.Constant;

import java.util.List;

import com.employee.Modal.EmployeeModal;

public class JsonData {
//	private String empNo;
//
//
//	private String empName;
//	
//	
//	private String department;
//	
//	
//	private String salary;
	
	List<EmployeeModal> emplList;
	
	private int  responseCode;
	private String responseMessege;


//	public String getEmpNo() {
//		return empNo;
//	}
//
//
//	public void setEmpNo(String empNo) {
//		this.empNo = empNo;
//	}
//
//
//	public String getEmpName() {
//		return empName;
//	}
//
//
//	public void setEmpName(String empName) {
//		this.empName = empName;
//	}


//	public String getDepartment() {
//		return department;
//	}
//
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}
//
//
//	public String getSalary() {
//		return salary;
//	}
//
//
//	public void setSalary(String salary) {
//		this.salary = salary;
//	}


	public int getResponseCode() {
		return responseCode;
	}


	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}


	public String getResponseMessege() {
		return responseMessege;
	}


	public void setResponseMessege(String responseMessege) {
		this.responseMessege = responseMessege;
	}


	public List<EmployeeModal> getEmplList() {
		return emplList;
	}


	public void setEmplList(List<EmployeeModal> emplList) {
		this.emplList = emplList;
	}
	
	
	
}
