package com.cic.employee.converter;

import com.cic.employee.dto.Employee;
import com.cic.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    public EmployeeEntity convertToEntity(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employee.getName());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setDepartment(employee.getDepartment());
        return employeeEntity;
    }

    public Employee convertToDTO(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setName(employeeEntity.getName());
        employee.setEmail(employeeEntity.getEmail());
        employee.setDepartment(employeeEntity.getDepartment());
        return employee;
    }
}
