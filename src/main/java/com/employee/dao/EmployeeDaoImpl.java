package com.employee.dao;




import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.employee.Modal.EmployeeModal;




@Repository("employeeDao")
public class EmployeeDaoImpl  implements EmployeeDao {
	private JdbcTemplate jdbcTemplate;
	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	public EmployeeDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void createEmployee(EmployeeModal empModal) {
		 if (empModal.getId() > 0) {
			 
			 System.out.println("id=ss="+empModal.getId());
		        // update
				
			 String sql = "UPDATE tbl_employee SET empno=?, empname=?, salary=?, " +
				  "department=?, joiningdate=? WHERE id=?"; jdbcTemplate.update(sql, empModal.getEmpNo(),
						  empModal.getEmpName(), empModal.getSalary(), empModal.getDepartment(),empModal.getJoiningDate(),
						  empModal.getId());
				 
		    } else {
		        // insert
		        String sql = "INSERT INTO tbl_employee (empno, empname,salary,department,joiningdate)"
		                    + " VALUES (?, ?, ? ,?,?)";
		        jdbcTemplate.update(sql, empModal.getEmpNo(), empModal.getEmpName(),empModal.getSalary(),empModal.getDepartment(),empModal.getJoiningDate());
		    }
		
		
	}

	public List<EmployeeModal> employeeList() {
		String sql = "SELECT * FROM tbl_employee";  
	    return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(EmployeeModal.class));  
	}

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM tbl_employee WHERE id=?";
	    jdbcTemplate.update(sql, id);
		
	}

	public List<EmployeeModal> searchEmployee(String searchText) {
		String sql = "select * from tbl_employee where empno =? or empname like '"+searchText+"%'";

		List<EmployeeModal> employees = this.jdbcTemplate
                .query(sql, 
                       new EmployeeMapper(),
                       searchText);
//        List<EmployeeModal> customers = jdbcTemplate.query(
//                sql,
//                new BeanPropertyRowMapper(EmployeeModal.class));

        return employees;
	}

	public EmployeeModal checkExistence(int empNo) {

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
	

}
