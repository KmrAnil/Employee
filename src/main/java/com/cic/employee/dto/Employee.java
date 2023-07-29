package com.cic.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Schema(description = "This DTO contains employee name, department and email")
public class Employee {
    @Schema(
            description = "Employee Name",
            name = "name",
            type = "String",
            example = "Ram Sharma"
    )
    private String name;
    @Schema(
            description = "Employee department",
            name = "department",
            type = "String",
            example = "IT"
    )
    private String department;
    @Schema(
            description = "Employee Email",
            name = "email",
            type = "String",
            example = "ram123@gmail.com"
    )
    private String email;
}
