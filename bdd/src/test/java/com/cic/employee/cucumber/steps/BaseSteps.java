package com.cic.employee.cucumber.steps;

import com.cic.employee.TestApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestApp.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class BaseSteps {

}
