
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Manager Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Contact List</h1>
            <h3><a href="/newEmployee">Add Employee</a></h3>
            <table border="1">
                <th>id</th>
                <th>Employee No</th>
                <th>Employee Name</th>
                <th>Department</th>
                <th>Salary</th>
               
                 
                <c:forEach var="emp" items="${empList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${emp.empNo}</td>
                    <td>${emp.empName}</td>
                    <td>${emp.department}</td>
                    <td>${emp.salary}</td>
                    <td>
                        <a href="/editContact?id=${emp.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/deleteContact?id=${emp.id}">Delete</a>
                    </td>
                             
                </tr>
                </c:forEach>             
            </table>
        </div>
    </body>
</html>