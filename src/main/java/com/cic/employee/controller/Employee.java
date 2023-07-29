package com.cic.employee.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class Employee {
    @GetMapping("/add")
    public String create(){
        return "anil";
    }
}
