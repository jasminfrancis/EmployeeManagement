package com.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.employee.Modal.EmployeeModal;

public class EmployeeMapper implements RowMapper<EmployeeModal>{

	public EmployeeModal mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeModal employee = new EmployeeModal();
		employee.setId(rs.getInt("id"));
        employee.setEmpNo(rs.getInt("empNo"));
        employee.setEmpName(rs.getString("empName"));
        employee.setSalary(rs.getInt("salary"));
        employee.setDepartment(rs.getString("department"));
        employee.setJoiningDate(rs.getString("joiningDate"));
        return employee;
		
	}

}
