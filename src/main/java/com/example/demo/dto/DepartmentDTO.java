package com.example.demo.dto;

import com.example.demo.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {

    @NotNull(message = "Department must have own name")
    @NotEmpty(message = "Department must have own valid, non empty name")
    private String departmentName;

    private String location;

    public static DepartmentDTO fromDepartment(Department department) {
        return new DepartmentDTO(
                department.getDepartmentName(),
                department.getLocation()
        );
    }
}
