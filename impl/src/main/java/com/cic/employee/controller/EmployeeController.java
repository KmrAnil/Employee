package com.cic.employee.controller;


import com.cic.employee.dto.Employee;
import com.cic.employee.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Objects.requireNonNull;

@RestController
public class EmployeeController implements IEmployeeController{

    private final IEmployeeService employeeService;

    @Autowired
    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = requireNonNull(employeeService);
    }

    @Override
    public Integer createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @Override
    public Employee getEmployee(Integer empId) {
        return employeeService.getEmployee(empId);
    }

    @Override
    public String updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @Override
    public String deleteEmployee(Integer empId) {
        return employeeService.deleteEmployee(empId);
    }

    @Override
    public String updateEmployeeDetail(Map<String,String> employee) {
        return employeeService.updateEmployeeDetail(employee);
    }
}
