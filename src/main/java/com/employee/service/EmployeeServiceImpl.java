package com.employee.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.employee.Constant.JsonData;
import com.employee.Constant.ResponseCode;
import com.employee.Constant.ResponseMessge;
import com.employee.Modal.EmployeeModal;
import com.employee.dao.EmployeeDao;


@Service("employeeService")

public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public JsonData createEmployee(int id,String empName, int salary, int empNo, String department,String joningDate,
			HttpServletRequest request) {
		JsonData jsonData = new JsonData();
		EmployeeModal empModal=new EmployeeModal();
		boolean duplicate=false;
			EmployeeModal checkExistence=employeeDao.checkExistence(empNo);
			if(checkExistence!=null) {
				if(checkExistence.getId()!=id) {
					duplicate=true;
				}
			}
		if(duplicate) {
			jsonData.setResponseCode(ResponseCode.DUPLICATE);
			jsonData.setResponseMessege(ResponseMessge.DUPLICATE);
		}else {
			empModal.setEmpName(empName);
			empModal.setDepartment(department);
			empModal.setSalary(salary);
			empModal.setJoiningDate(joningDate);
			empModal.setEmpNo(empNo);
			empModal.setId(id);
			employeeDao.createEmployee(empModal);
			jsonData.setResponseCode(ResponseCode.SUCESS_MESSAGE);
			jsonData.setResponseMessege(ResponseMessge.SUCESS_MESSAGE);
		}
		return jsonData;
	}



	public JsonData employeeList() {
		JsonData jsonData = new JsonData();
		List<EmployeeModal> empList=employeeDao.employeeList();
		if(empList.isEmpty()) {
			jsonData.setResponseCode(ResponseCode.NOT_FOUND);
			jsonData.setResponseMessege(ResponseMessge.NOT_FOUND);
		}else {
			jsonData.setResponseCode(ResponseCode.SUCESS_MESSAGE);
			jsonData.setResponseMessege(ResponseMessge.DATA_LIST);
			jsonData.setEmplList(empList);
		}	
		return jsonData;
	}

	public JsonData deleteEmployee(int id) {
		JsonData jsonData = new JsonData();
		employeeDao.deleteEmployee(id);
		jsonData.setResponseCode(ResponseCode.SUCESS_MESSAGE);
		jsonData.setResponseMessege(ResponseMessge.DELETE_RECORD);
		return jsonData;
	}



	public JsonData searchEmployee(String searchText) {
		JsonData jsonData = new JsonData();
		List<EmployeeModal> empList=employeeDao.searchEmployee(searchText);
		if(empList.isEmpty()) {
			jsonData.setResponseCode(ResponseCode.NOT_FOUND);
			jsonData.setResponseMessege(ResponseMessge.NOT_FOUND);
		}else {
			jsonData.setResponseCode(ResponseCode.SUCESS_MESSAGE);
			jsonData.setResponseMessege(ResponseMessge.DATA_LIST);
			jsonData.setEmplList(empList);
		}	
		return jsonData;
	}

	
	
	
}
