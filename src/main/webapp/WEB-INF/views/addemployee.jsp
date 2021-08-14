<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Page Title</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<form>
	<table>
	<tr>
			
			<td><input type="hidden"  id="txtId"   name="txtId" /></td>
		</tr>
		
		<tr>
			<td>Employee Name:</td>
			<td><input type="text" id="empName" maxlength="100" name="empName" /></td>
		</tr>
		<tr>
			<td>Employee No:</td>
			<td><input type="text" id="empNo" maxlength="10" data-rule-maxlength="10"  oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength); this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  name="empNo" /></td>
		</tr>
		<tr>
					<td>Date Of joining:</td>
			<td> <input type="text" id="joiningDate" placeholder="dd/MM/yyyy" id="joiningDate"/> </td>
		
		<tr>
			<td>Department:</td>
			<td><select id="department"  >
					<option value="AD">Administration</option>
					<option value="IT">Information Technology</option>
					<option value="HD">Help Desk</option>
					<option value="HR">Human Resource</option>
					<option value="OP">Operation</option>

			</select></td>
		</tr>
		<tr>
			<td>Salary:</td>
			<td><input type="text" id="salary" maxlength="10" data-rule-maxlength="10"  oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength); this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"  name="salary" /></td>
		</tr>
	</table>
	<input type="text" style="display: none;" name="taskInstanceId"
		id="taskInstanceId">
		 <input type="button" value="Save"
		id="saveButton" /> 
		<input class="clearButton" id="clearNew"
		value="Clear" type="button" />
</form>

<label for="search">Search:</label>
<input type="text" id="search" name="q"
       aria-label="Search through site content">

<button onclick="searchEmployee()">Search</button>

<table id="tblEmployee" border="1" style="border-collapse:collapse">
            <thead>
            
                <tr>
                    <th>Employee No</th>
                    <th>Employee Name</th>
                    <th>Salary</th>
                    <th>Department</th>
                     <th>Joning Date</th>
                    <th>Actions</th>
                    
                </tr>
                
            </thead>
            <tbody>
            
            </tbody>
        </table>
        
      
<script type="text/javascript">
$(document).ready(function () {
getEmployeeList();
var employee = {};

 $('#saveButton').click(function () {
	 if ($('#empNo').val() ) {
		    employee.empNo = $('#empNo').val();  
		  }else{
			  alert("Employee No must be filled out");
			    return false; 
		  }
		if($('#empName').val()){
			employee.empName = $('#empName').val();
		  }else{
			  alert("Employee name  must be filled out");
			    return false;  
		  }
		if($('#department :selected').val()){
			 
			    employee.department = $('#department :selected').val();
		  }else{
			  alert("department  must be filled out");
			    return false;
		  }
	 
		if($('#salary').val()){
			  
			    employee.salary = $('#salary').val();
		  }else{
			  alert("Salary  must be filled out");
			    return false;
		  }
		
		if($('#joiningDate').val()){
			  
		    employee.joiningDate = $('#joiningDate').val();
	  }else{
		  alert("Joining  must be filled out");
		    return false;
	  }
		
	 
	
	if($('#txtId').val()){
		employee.id = $('#txtId').val();
	}
	
	


	$.ajax({
		url : 'saveEmployee',
		method : 'POST',
		data : JSON.stringify({
			"empNo" : employee.empNo,
			"empName" : employee.empName,
			"department" : employee.department,
			"salary":employee.salary,
			"id":employee.id,
			"joiningDate":employee.joiningDate
			
		}),
		//data: JSON.stringify(employee),
		contentType : "application/json;charset=UTF-8",
		dataType : 'json',
		success : function(data) {
			alert(data.responseMessege);			
			getEmployeeList();
			
		},
		error : function(error) {
			alert(error);
		}
	})
	 

 })
 
 /* Clear the data*/
 $('#clearNew').click(function () {
 		
 		$('#empNo').val('');
        $('#empName').val('');
        $('#salary').val('');
        $('#joiningDate').val('');
 		
 	});
 
});

function searchEmployee(){
	
	var searchText=$('#search').val();
	 $.ajax({
	        url: 'employeeSearch/'+searchText,
	        method: 'GET',
	        success: function (data) {
	            alert('search result');
	            console.log(data.emplList);
	            var tableBody = $('#tblEmployee tbody');
	          var listData=  data.emplList;
	            
	            tableBody.empty();
	            $(listData).each(function (index, element) {
	                tableBody.append('<tr><td>'+element.empNo+'</td><td>'+element.empName+'</td><td>'+element.salary+'</td><td>'+element.department+'</td><td>'+element.joiningDate+'</td><td><button onclick = updateEmployee('+element.id+',"'+element.empNo+'","'+element.empName+'","'+element.salary+'","'+element.department+'","'+element.joiningDate+'")>Update</button> | <button onclick = "deleteEmployee('+element.id+')">Delete</button></td></tr>');
	            })
	        },
	        error: function (error) {
	            alert(error);
	        }
	    })

}

/* Employee List*/
function getEmployeeList() {
    $.ajax({
        url: 'employeeList',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
        	console.log(data);
        	console.log(data.emplList);
            var tableBody = $('#tblEmployee tbody');
          var listData=  data.emplList;
          
           
            	tableBody.empty();
                $(listData).each(function (index, element) {
                    tableBody.append('<tr><td>'+element.empNo+'</td><td>'+element.empName+'</td><td>'+element.salary+'</td><td>'+element.department+'</td><td>'+element.joiningDate+'</td><td><button onclick = updateEmployee('+element.id+',"'+element.empNo+'","'+element.empName+'","'+element.salary+'","'+element.department+'","'+element.joiningDate+'")>Update</button> | <button onclick = "deleteEmployee('+element.id+')">Delete</button></td></tr>');
                })
           
            
        },
        error: function (error) {
            alert(error);
        }
    });
}

function deleteEmployee(id){


    $.ajax({
        url: 'deleteEmployee/'+id,
        method: 'DELETE',
        success: function (data) {
            alert('record has been deleted');
            getEmployeeList();
        },
        error: function (error) {
            alert(error);
        }
    })
}

/*This function set all available values in the currecponding fields and update the data*/
function updateEmployee(id,empNo,empName,salary,department,joiningDate){ 
	
	
	
	 $('#empNo').val(empNo);
     $('#empName').val(empName);
     $('#txtId').val(id);
     $('#salary').val(salary);
     $('#department').val(department);
     $('#joiningDate').val(joiningDate);
}
	
		

		

	
</script>
</body>
</html>