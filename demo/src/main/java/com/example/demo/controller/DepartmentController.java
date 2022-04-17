package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.service.DeparmentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class DepartmentController {


    private DeparmentServiceImpl departmentService;

    @PostMapping("/departments")
    public Department addDepartment(@RequestBody Department department){
        departmentService.addDepartment(department);
        return department;
    }

    @RequestMapping("/departments")
    public List<Department> showAllDepartments(){
        List<Department> departments =departmentService.getAllDepartments();
        return departments;
    }

    @GetMapping("/departments/{id}")

    public Department getDepartment(@PathVariable String id){
        Department department = departmentService.getDepartment(id);
        return department;
    }
}
