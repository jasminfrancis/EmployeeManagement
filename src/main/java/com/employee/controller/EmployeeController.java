/**
 * @author Jasmin
 * Employee management purpose
 */
package com.employee.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.employee.Modal.EmployeeModal;
import com.employee.service.EmployeeService;
import com.google.gson.Gson;



@Controller
public class EmployeeController {
	String message = "Hello ";

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 
	 * Jasmin Aug 14, 2021
	 * This service return to web page screen.
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping("/")
	public String showPage() throws Exception {
		return "addemployee";

	}

	/**
	 * 
	 * Jasmin Aug 14, 2021
	 * Purpose : This service is used for saving an updating 
	 * employee details and also the service redirect 
	 * to the base controller method for stay on the same webpage
	 * @param employee
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String saveEmployee(@RequestBody EmployeeModal employee, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String encodedResponse = "";
		ModelAndView mv = new ModelAndView("redirect:/");
//		encodedResponse = new String(new Gson()
//				.toJson(employeeService.createEmployee(employee.getId(), employee.getEmpName(), employee.getSalary(),
//						employee.getEmpNo(), employee.getDepartment(), employee.getJoiningDate(), request))
//				.getBytes("UTF-8"), "ISO-8859-1");
		encodedResponse = new String(new Gson()
				.toJson(employeeService.createEmployee(employee, request))
				.getBytes("UTF-8"), "ISO-8859-1");
		return encodedResponse;

	}

	/**
	 * Jasmin Aug 14, 2021
	 * Purpose:Service getting all employee records
	 * @return
	 * @throws Exception String
	 */
	
	@RequestMapping(value = "/employeeList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String employeeList() throws Exception {
		String encodedResponse = "";
		encodedResponse = new String(new Gson().toJson(employeeService.employeeList()).getBytes("UTF-8"), "ISO-8859-1");
		return encodedResponse;
	}

	/**
	 * 
	 * Jasmin Aug 14, 2021
	 * Purpose:Search employee details based on the employee no and employee name
	 * @param searchText
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping(value = "/employeeSearch/{searchText}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String edit(@PathVariable String searchText) throws Exception {
		String encodedResponse = "";
		encodedResponse = new String(new Gson().toJson(employeeService.searchEmployee(searchText)).getBytes("UTF-8"),
				"ISO-8859-1");
		return encodedResponse;

	}
	
	/**
	 * 
	 * Jasmin Aug 14, 2021
	 * Purpose:Delete employee based on the id
	 * @param id
	 * @return
	 * @throws Exception String
	 */
	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String delete(@PathVariable int id) throws Exception {
		System.out.println("id" + id);
		String encodedResponse = "";
		encodedResponse = new String(new Gson().toJson(employeeService.deleteEmployee(id)).getBytes("UTF-8"),
				"ISO-8859-1");

		return encodedResponse;
	}
}
