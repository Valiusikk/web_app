package com.example.demo.dto;

import com.example.demo.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private String departmentName;

    private String location;

    public static DepartmentDTO fromDepartment(Department department) {
        return new DepartmentDTO(
                department.getDepartmentName(),
                department.getLocation()
        );
    }
}
