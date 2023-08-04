package com.cic.employee.controller;


import com.cic.employee.dto.Employee;
import com.cic.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.requireNonNull;

@RestController
public class EmployeeController implements IEmployeeController{

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = requireNonNull(employeeService);
    }

    @Override
    public String createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @Override
    public Employee getEmployee(String email) {
        return employeeService.getEmployee(email);
    }

    @Override
    public String updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @Override
    public String deleteEmployee(String email) {
        return employeeService.deleteEmployee(email);
    }
}
