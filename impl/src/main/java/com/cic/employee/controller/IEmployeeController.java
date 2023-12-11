package com.cic.employee.controller;

import com.cic.employee.dto.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This {@link IEmployeeController} interface define the rest end point to perform Employee crud operation
 *
 * <ul>
 *     <li>To add employee detail, use {@link #createEmployee(Employee)}</li>
 *     <li>To update employee detail, use {@link #updateEmployee(Employee)}</li>
 *     <li>To add employee detail, use {@link #getEmployee(Integer)}</li>
 *     <li>To add employee detail, use {@link #deleteEmployee(Integer)}</li>
 * </ul>
 *
 */

@RequestMapping
public interface IEmployeeController {

    @Operation(summary = "Employee detail create API",
    description = "This API is used to add employee detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee detail added"),
            @ApiResponse(responseCode = "400", description = "Invalid Employee detail"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PostMapping("/create")
    Integer createEmployee(@RequestBody Employee employee);

    @Operation(summary = "Employee detail get API",
            description = "This API is used to get employee detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee detail fetched"),
            @ApiResponse(responseCode = "400", description = "Invalid Employee detail"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @GetMapping("get/{empId}")
    Employee getEmployee(@PathVariable Integer empId);

    @Operation(summary = "Employee detail update API",
            description = "This API is used to update employee detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee detail updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Employee detail"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PutMapping("/update")
    String updateEmployee(@RequestBody Employee employee);

    @Operation(summary = "Employee detail delete API",
            description = "This API is used to delete employee detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee detail Deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid Employee detail"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @DeleteMapping("delete/{email}")
    String deleteEmployee(@PathVariable Integer emailId);

    @Operation(summary = "Employee detail update API",
            description = "This API is used to update employee detail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee detail updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Employee detail"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PatchMapping("/update-detail")
    String updateEmployeeDetail(@RequestBody Map<String,String> employee);
}
