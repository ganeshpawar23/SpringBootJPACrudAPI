package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmployeeService {
	Employee createEmployee(Employee Employee);

    Employee getEmployeeById(Long EmployeeId);

    List<Employee> getAllEmployees();

    Employee updateEmployee(Employee Employee);

    void deleteEmployee(Long EmployeeId);

}
