package com.cic.employee.cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        glue={"com.cic.employee.cucumber"},
        plugin = {"pretty","html:target/html-reports/report.html"}
)
public class CucumberRunner {
}
