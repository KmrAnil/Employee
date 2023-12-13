package com.cic.employee.service;

import com.cic.employee.dto.Employee;
import com.cic.employee.dto.Post;

import java.util.Map;

public interface IEmployeeService {
    Integer createEmployee(Employee employee);

    Employee getEmployee(Integer empId);

    String updateEmployee(Employee employee);

    String deleteEmployee(Integer empId);

    String updateEmployeeDetail(Map<String,String> employee);

    Post enternalAPICall(Integer empId);
}
