package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    @RequestMapping("/employees")
    public List<EmployeeDTO> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")

    public EmployeeDTO getEmployee(@PathVariable UUID id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public EmployeeDTO addNewEmployee(@RequestBody EmployeeDTO employee) {

        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
    }
}
