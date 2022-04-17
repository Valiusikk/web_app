package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeServiceImpl employeeService;

    @RequestMapping("/employees")
    public List<Employee> showAllEmployees(){
        List<Employee> employees =employeeService.getAllEmployees();
        return employees;
    }

    @GetMapping("/employees/{id}")

    public Employee getEmployee(@PathVariable String id){
        Employee employee = employeeService.getEmployee(id);
        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable String id){
        Employee employee = employeeService.getEmployee(id);
        employeeService.deleteEmployee(id);
    }
}
