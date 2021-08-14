package com.employee.service;



import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.Constant.JsonData;
import com.employee.Constant.ResponseCode;
import com.employee.Constant.ResponseMessge;
import com.employee.Modal.EmployeeModal;
import com.employee.dao.EmployeeDao;


@Service("employeeService")

public class EmployeeServiceImpl implements EmployeeService {
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	private EmployeeDao employeeDao;

	public JsonData createEmployee(EmployeeModal employee,
			HttpServletRequest request) {
		log.info("############# Create Employee Data Data {}", employee);
		JsonData jsonData = new JsonData();
		boolean duplicate=false;
			EmployeeModal checkExistence=employeeDao.checkExistence(employee.getEmpNo());
			if(checkExistence!=null) {
				if(checkExistence.getId()!=employee.getId()) {
					duplicate=true;
				}
			}
		if(duplicate) {
			jsonData.setResponseCode(ResponseCode.DUPLICATE);
			jsonData.setResponseMessege(ResponseMessge.DUPLICATE);
		}else {
			employeeDao.createEmployee(employee);
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
