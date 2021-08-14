package com.employee.controller;



import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.employee.Modal.EmployeeModal;
import com.employee.service.EmployeeService;
import com.google.gson.Gson;






@Controller
public class EmployeeController {
	String message = "Hello ";
	 
	
	@Autowired 
	private  EmployeeService employeeService;
	
	/*
	 * @RequestMapping("/") public ModelAndView showMessage() throws Exception {
	 * //List<Contact> listContact = contactDAO.list()
	 * System.out.println("------------------------");
	 * 
	 * 
	 * List<EmployeeModal> empList=employeeService.employeeList(); ModelAndView
	 * mv=new ModelAndView(); mv.addObject("empList", empList);
	 * mv.setViewName("home"); return mv; }
	 */
	
	@RequestMapping("/")
	public String showMessage() throws Exception {
	return "addemployee";
    
    


	}
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getTime(@RequestBody EmployeeModal employee, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

     
      String encodedResponse = "";
      ModelAndView mv=new ModelAndView("redirect:/");
		encodedResponse = new String(
				new Gson().toJson(employeeService.createEmployee(employee.getId(),employee.getEmpName(),
						employee.getSalary(), employee.getEmpNo(),employee.getDepartment(),employee.getJoiningDate(),request)).getBytes("UTF-8"),
				"ISO-8859-1");

		return encodedResponse;
     
    }
	
	


	@RequestMapping(value = "/employeeList", method = RequestMethod.GET,
	 produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String employeeList() throws Exception {
		System.out.println("-------------emplist");
		
		String encodedResponse = "";
		encodedResponse = new String(
				new Gson().toJson(employeeService.employeeList()).getBytes("UTF-8"),
				"ISO-8859-1");

		return encodedResponse;
	}
		
		@RequestMapping(value = "/employeeSearch/{searchText}", method = RequestMethod.GET,
				 produces = MediaType.APPLICATION_JSON_VALUE)
				public @ResponseBody String edit(@PathVariable String searchText) throws Exception {
					System.out.println("-------------id::"+searchText);
					String encodedResponse = "";
					encodedResponse = new String(
							new Gson().toJson(employeeService.searchEmployee(searchText)).getBytes("UTF-8"),
							"ISO-8859-1");
					return encodedResponse;

	}
		
		@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody String delete(@PathVariable int id) throws Exception {
			System.out.println("id"+id);
			String encodedResponse = "";
			encodedResponse = new String(
					new Gson().toJson(employeeService.deleteEmployee(id)).getBytes("UTF-8"),
					"ISO-8859-1");

			return encodedResponse;
		}
}
