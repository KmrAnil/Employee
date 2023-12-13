package com.cic.employee.cucumber.dto;

import lombok.Data;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
public class ApiBinding {
    private String url;
    private HttpMethod httpMethod;
    private String mediaType;
    private Integer repetitions;
    private Map<String,String> responses;
}
