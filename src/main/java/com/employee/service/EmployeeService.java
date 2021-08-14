package com.employee.service;



import javax.servlet.http.HttpServletRequest;

import com.employee.Constant.JsonData;
import com.employee.Modal.EmployeeModal;






public interface EmployeeService {

	

	public JsonData createEmployee(EmployeeModal employee,
			HttpServletRequest request);

	//public List<EmployeeModal> employeeList();

	public JsonData employeeList();

	public JsonData deleteEmployee(int id);

	public JsonData searchEmployee(String searchText);

}
