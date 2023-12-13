package com.cic.employee.cucumber.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MockBindings {
    private String name;
    private Map<String,ApiBinding> bindings;
}
