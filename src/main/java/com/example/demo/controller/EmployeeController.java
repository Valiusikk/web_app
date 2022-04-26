package com.example.demo.controller;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeDTO> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{email}")
    public EmployeeDTO getEmployee(@Valid @Email @NotNull @NotEmpty @NotBlank @PathVariable String email) {
        return employeeService.getEmployee(email);
    }

    @PostMapping("/employees")
    public EmployeeDTO addNewEmployee(@Valid @RequestBody EmployeeDTO employee) {
        return employeeService.saveEmployee(employee);
    }

    @PatchMapping("/employees")
    public EmployeeDTO updateEmployee(@Valid @RequestBody EmployeeDTO employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/{email}")
    public EmployeeDTO deleteEmployee(@PathVariable String email) {
        return employeeService.deleteEmployee(email);
    }
}
