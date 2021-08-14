package com.employee.service;



import javax.servlet.http.HttpServletRequest;

import com.employee.Constant.JsonData;






public interface EmployeeService {

	

	public JsonData createEmployee(int id,String empName, int salary, int empNo, String department,String joningData,
			HttpServletRequest request);

	//public List<EmployeeModal> employeeList();

	public JsonData employeeList();

	public JsonData deleteEmployee(int id);

	public JsonData searchEmployee(String searchText);

}
