package com.example.demo.dto;

import com.example.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Employee must have valid first name")
    @NotNull(message = "Employee's first name must not be null")
    @NotEmpty(message = "Employee's first name must not be empty string")
    private String employeeName;

    @NotBlank(message = "Employee must have valid surname")
    @NotNull(message = "Employee's surname must not be null")
    @NotEmpty(message = "Employee's surname must not be empty string")
    private String employeeSurname;

    @NotNull(message = "Employee's phone number should be not null")
    @NotBlank(message = "Each employee should have own unique phone number")
    @NotEmpty(message = "Employee's phone number should be not an empty string")
    private String phoneNumber;

    @Min(value = 1L,message = "Minimal salary of employee is 1 monetary unit")
    private float salary;

    @Email
    @NotNull(message = "Employee email should not be null")
    @NotBlank(message = "Employee email should contain valid email")
    @NotEmpty(message = "Employee email should not be empty string")
    private String email;

    @NotNull(message = "Employee should have department")
    private DepartmentDTO departmentDto;

    public static EmployeeDTO fromEmployee(Employee employee) {
        return new EmployeeDTO(
                employee.getName(),
                employee.getSurname(),
                employee.getPhoneNumber(),
                employee.getSalary(),
                employee.getEmail(),
                DepartmentDTO.fromDepartment(employee.getDepartmentId())
        );
    }
}
