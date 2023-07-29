package com.cic.employee.service;

import com.cic.employee.converter.EmployeeConverter;
import com.cic.employee.dto.Employee;
import com.cic.employee.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.requireNonNull;

@Service
public class EmployeeService implements IEmployeeService{


    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeConverter employeeConverter) {
        this.employeeRepository = requireNonNull(employeeRepository);
        this.employeeConverter = requireNonNull(employeeConverter);
    }

    @Override
    public String createEmployee(Employee employee) {
        employeeRepository.save(employeeConverter.convertToEntity(employee));
        return "Employ Detail Added";
    }

    @Override
    public Employee getEmployee(String email) {
        return employeeConverter.convertToDTO(employeeRepository.findByEmail(email));
    }

    @Override
    public String updateEmployee(Employee employee) {
        //TODO: update only 1-2 parameter
        return null;
    }

    @Override
    public String deleteEmployee(String empId) {
        return null;
    }
}
