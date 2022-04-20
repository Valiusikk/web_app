package com.example.demo.dto;

import com.example.demo.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    @NotNull(message = "Department must have own name")
    @Pattern(regexp = "[a-zA-Z]")
    private String departmentName;

    private String location;

    public static DepartmentDTO fromDepartment(Department department) {
        return new DepartmentDTO(
                department.getDepartmentName(),
                department.getLocation()
        );
    }
}
