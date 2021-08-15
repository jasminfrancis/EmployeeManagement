/**
 * @author Jasmin
 * Employee management database operations
 */
package com.employee.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.employee.Modal.EmployeeModal;

@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger log = LoggerFactory.getLogger(EmployeeDaoImpl.class);
	private JdbcTemplate jdbcTemplate;

	public EmployeeDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * Adding and editing employee details
	 */
	public void createEmployee(EmployeeModal empModal) {
		log.info("############# Create Employee EmployeeDaoImpl class##########");
		if (empModal.getId() > 0) {
			// update

			String sql = "UPDATE tbl_employee SET empno=?, empname=?, salary=?, "
					+ "department=?, joiningdate=? WHERE id=?";
			jdbcTemplate.update(sql, empModal.getEmpNo(), empModal.getEmpName(), empModal.getSalary(),
					empModal.getDepartment(), empModal.getJoiningDate(), empModal.getId());
			log.info("############# Create Employee EmployeeDaoImpl Updated##########");

		} else {
			// insert
			String sql = "INSERT INTO tbl_employee (empno, empname,salary,department,joiningdate)"
					+ " VALUES (?, ?, ? ,?,?)";
			jdbcTemplate.update(sql, empModal.getEmpNo(), empModal.getEmpName(), empModal.getSalary(),
					empModal.getDepartment(), empModal.getJoiningDate());
			log.info("############# Create Employee EmployeeDaoImpl new registration##########");
		}

	}

	/*
	 * List of all registered employee data
	 */
	public List<EmployeeModal> employeeList() {
		log.info("#######EmployeeDaoImpl employeeList()##########");
		String sql = "SELECT * FROM tbl_employee";
		return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeModal.class));
	}

	/*
	 * Delete employee from the record
	 */
	public void deleteEmployee(int id) {
		log.info("#############  EmployeeDaoImpl deleteEmployee()##########");
		String sql = "DELETE FROM tbl_employee WHERE id=?";
		jdbcTemplate.update(sql, id);

	}

	/*
	 * Search employee details based on the employee no and name
	 */
	public List<EmployeeModal> searchEmployee(String searchText) {
		log.info("#############  EmployeeDaoImpl searchEmployee()##########");
		String sql = "select * from tbl_employee where empno =? or empname like '" + searchText + "%'";
		List<EmployeeModal> employees = this.jdbcTemplate.query(sql, new EmployeeMapper(), searchText);
		return employees;
	}

	/*
	 * Check employee no is is existing or not
	 */
	public EmployeeModal checkExistence(int empNo) {
		log.info("#############  EmployeeDaoImpl checkExistence()##########");
		try {
			String query = "Select * from tbl_employee Where empno = ? ";
			EmployeeModal emp = this.jdbcTemplate.queryForObject(query, new EmployeeMapper(), empNo);
			return emp;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}

	}

	/*
	 * Get employee details based on the id
	 */
	public EmployeeModal findById(int id) {
		log.info("#############  EmployeeDaoImpl findById()##########");
		try {
			String query = "Select * from tbl_employee Where id = ? ";
			EmployeeModal emp = this.jdbcTemplate.queryForObject(query, new EmployeeMapper(), id);
			return emp;
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}

	}

}
