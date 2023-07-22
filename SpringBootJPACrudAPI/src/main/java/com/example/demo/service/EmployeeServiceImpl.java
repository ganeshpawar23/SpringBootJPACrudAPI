package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.NoSuchEmployeeExistsException;
import com.example.demo.repository.EmployeeRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public Employee getEmployeeById(Long employeeId) {
		Optional<Employee> optionalEmp=employeeRepository.findById(employeeId);		
		return optionalEmp.get();
	}

	@Override
	public List<Employee> getAllEmployees() {		
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		Employee existEmp=employeeRepository.findById(employee.getId()).orElse(null);
		System.out.println("existEmp:==>"+existEmp);
		if(existEmp!=null) {
			existEmp.setFirstName(employee.getFirstName());
			existEmp.setLastName(employee.getLastName());
			existEmp.setEmail(employee.getEmail());
			existEmp.setAge(employee.getAge());
			Employee updatedEmp=employeeRepository.save(existEmp);
			return updatedEmp;
		}
		else return null;
		
			
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

}
