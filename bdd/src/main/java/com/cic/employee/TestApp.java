package com.cic.employee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan("${test.scan.packages:com.cic.employee}")
@Configuration
public class TestApp {
}
