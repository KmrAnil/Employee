package com.cic.employee.service;

import com.cic.employee.dto.Post;
import com.cic.employee.util.EmployeeUtility;
import com.cic.employee.converter.EmployeeConverter;
import com.cic.employee.dto.Employee;
import com.cic.employee.entity.EmployeeEntity;
import com.cic.employee.repo.EmployeeRepository;
import com.cic.employee.util.ExternalAPIUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;
    private final EmployeeUtility employeeUtility;
    private final ObjectMapper objectMapper;
    private final ExternalAPIUtil externalAPIUtil;

    @Autowired
    public EmployeeService(
            EmployeeRepository employeeRepository,
            EmployeeConverter employeeConverter,
            EmployeeUtility employeeUtility,
            ObjectMapper objectMapper, ExternalAPIUtil externalAPIUtil) {
        this.employeeRepository = requireNonNull(employeeRepository);
        this.employeeConverter = requireNonNull(employeeConverter);
        this.employeeUtility = requireNonNull(employeeUtility);
        this.objectMapper = requireNonNull(objectMapper);
        this.externalAPIUtil = externalAPIUtil;
    }

    @Override
    public Integer createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeRepository.save(employeeConverter.convertToEntity(employee));
        return employeeEntity.getId();
    }

    @Override
    @CircuitBreaker(name = "employeeService", fallbackMethod = "getEmployeeFallBack")
    public Employee getEmployee(Integer empId) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(empId);
        if(employee.isPresent())
            return employeeConverter.convertToDTO(employee.get());
        else
            throw new RuntimeException("Employee Not Found");
    }

    public Employee getEmployeeFallBack(Integer empId, Throwable throwable){
        log.info("In log back method");
        throw new RuntimeException("Employee get api call failed - "+throwable.getMessage(),throwable);
    }

    @Override
    public String updateEmployee(Employee employee) {
        employeeRepository.save(employeeConverter.convertToEntity(employee));
        return null;
    }

    @Override
    public String deleteEmployee(Integer empId) {
        employeeRepository.deleteById(empId);
        return "Detail Removed";
    }

    @Override
    public String updateEmployeeDetail(Map<String,String> updates) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmail(updates.get("email"));
        EmployeeEntity cloneEmployeeEntity = employeeUtility.clone(employeeEntity);

        Map oldMap = objectMapper.convertValue(cloneEmployeeEntity,Map.class);
        oldMap.putAll(updates);

        cloneEmployeeEntity = objectMapper.convertValue(oldMap,EmployeeEntity.class);

        employeeRepository.save(employeeUtility.copyEmployeeDetail(cloneEmployeeEntity,employeeEntity));
        return "Detail updated";
    }

    @Override
    public Post enternalAPICall(Integer postId) {
        return externalAPIUtil.getPosts(postId);
    }
}
