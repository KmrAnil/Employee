package com.cic.employee.cucumber.steps;

import com.cic.employee.controller.IEmployeeController;
import com.cic.employee.cucumber.beans.EmployeeBean;
import com.cic.employee.cucumber.config.MockServer;
import com.cic.employee.dto.Employee;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EmployeeSteps {
    @Autowired
    private IEmployeeController iEmployeeController;
    @Autowired
    private EmployeeBean employeeBean;

    @Autowired
    private MockServer mockServer;

    @When("employee is created with name {string} department {string} and email {string}")
    public void employeeIsCreatedWithNameDepartmentAndEmail(String name, String department, String email) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setDepartment(department);
        employee.setEmail(email);
        Integer empId = iEmployeeController.createEmployee(employee);
        log.info("Employee detail is add with empId {}",empId);
        Assert.assertNotNull(empId);
        employeeBean.setEmpId(empId);
        employeeBean.setEmail(email);
    }


    @Then("verify employee detail is added")
    public void verifyEmployeeDetailIsAddedWithEmail() {
        Employee employee = iEmployeeController.getEmployee(employeeBean.getEmpId());
        Assert.assertNotNull(employee);
        Assert.assertEquals(employeeBean.getEmail(),employee.getEmail());
    }

    @Then("update employee department as {string}")
    public void updateEmployeeDepartmentForEmail(String department) {
        Map<String,String> updateDetails = new HashMap<>();
        updateDetails.put("email",employeeBean.getEmail());
        updateDetails.put("department",department);
        String s = iEmployeeController.updateEmployeeDetail(updateDetails);
        Assert.assertEquals("Detail updated",s);
    }

    @Then("delete the employee detail")
    public void deleteTheEmployeeWithEmail() {
        iEmployeeController.deleteEmployee(employeeBean.getEmpId());
    }

    @When("get post for id {int}")
    public void getPostForId(int postId) {
        Map<String,String> placeHolder = new HashMap<>();
        placeHolder.put("POST_ID", String.valueOf(1));
        placeHolder.put("TITLE","Title from placeholder");
        mockServer.configure("POST_API","SUCCESS",placeHolder,1);
        iEmployeeController.enternalAPICall(postId);
    }
}
