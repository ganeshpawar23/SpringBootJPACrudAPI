package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.exception.NoSuchEmployeeExistsException;
import com.example.demo.service.EmployeeService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		Employee saveEmp=employeeService.createEmployee(employee);
		return new ResponseEntity<>(saveEmp,HttpStatus.CREATED);
		
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id)
	{
		Employee employee=employeeService.getEmployeeById(id);
		if(employee!=null)
		return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		else
			return null;
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployee()
	{
		List<Employee> employee=employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(employee,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateEmployee(@PathVariable("id") Long id,@RequestBody Employee employee)
	{		
		employee.setId(id);
		Employee emp=employeeService.updateEmployee(employee);
		System.out.println("in controller"+emp);
		if(emp==null)
			return new ResponseEntity<>(new NoSuchEmployeeExistsException("No such employee found !!"),HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(emp,HttpStatus.OK);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id)
	{
		employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted !!",HttpStatus.OK);
		
		
	}
	
}
