package com.cic.employee.cucumber.beans;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data
@Component
@Scope(scopeName = "cucumber-glue", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EmployeeBean {
    private Integer empId;
    private String email;
}
