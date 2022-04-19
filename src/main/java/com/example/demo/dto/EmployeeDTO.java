package com.example.demo.dto;

import com.example.demo.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private String employeeName;

    private String employeeSurname;

    private String phoneNumber;

    private float salary;

    private String email;

    private DepartmentDTO departmentDto;

    public static EmployeeDTO fromEmployee(Employee employee){
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
