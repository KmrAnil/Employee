package com.cic.employee.util;

import com.cic.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUtility {

    public EmployeeEntity clone(EmployeeEntity existingEmployeeEntity){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(existingEmployeeEntity.getName());
        employeeEntity.setEmail(existingEmployeeEntity.getEmail());
        employeeEntity.setDepartment(existingEmployeeEntity.getDepartment());
        return employeeEntity;
    }

    public EmployeeEntity copyEmployeeDetail(EmployeeEntity cloneEmployeeEntity, EmployeeEntity employeeEntity) {
        employeeEntity.setDepartment(cloneEmployeeEntity.getDepartment());
        employeeEntity.setName(cloneEmployeeEntity.getName());
        return employeeEntity;
    }
}
