package com.employee.dao;

import java.util.List;

import com.employee.Modal.EmployeeModal;

public interface EmployeeDao {

	void createEmployee(EmployeeModal empModal);

	List<EmployeeModal> employeeList();

	void deleteEmployee(int id);

	List<EmployeeModal> searchEmployee(String searchText);

	
	EmployeeModal checkExistence(int empNo);

}
