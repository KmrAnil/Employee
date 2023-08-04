package com.cic.employee.service;

import com.cic.employee.util.EmployeeUtility;
import com.cic.employee.converter.EmployeeConverter;
import com.cic.employee.dto.Employee;
import com.cic.employee.entity.EmployeeEntity;
import com.cic.employee.repo.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.Objects.requireNonNull;

@Service
public class EmployeeService implements IEmployeeService{


    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final EmployeeUtility employeeUtility;
    private final ObjectMapper objectMapper;

    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeConverter employeeConverter,
            EmployeeUtility employeeUtility,
            ObjectMapper objectMapper) {
        this.employeeRepository = requireNonNull(employeeRepository);
        this.employeeConverter = requireNonNull(employeeConverter);
        this.employeeUtility = requireNonNull(employeeUtility);
        this.objectMapper=requireNonNull(objectMapper);
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

    @Override
    public String updateEmployeeDetail(Map<String,String> updates) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmail(updates.get("email"));
        EmployeeEntity cloneEmployeeEntity = employeeUtility.clone(employeeEntity);

        Map oldMap = objectMapper.convertValue(cloneEmployeeEntity,Map.class);
        oldMap.putAll(updates);

        cloneEmployeeEntity = objectMapper.convertValue(oldMap,EmployeeEntity.class);
        ;
        employeeRepository.save(employeeUtility.copyEmployeeDetail(cloneEmployeeEntity,employeeEntity));
        return "Data updated";
    }
}
