package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class DepartmentController {

    private DepartmentServiceImpl departmentService;

    @PostMapping(value = "/departments", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Department addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @RequestMapping("/departments")
    public List<DepartmentDTO> showAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public DepartmentDTO getDepartment(@PathVariable UUID id) {
        return departmentService.getDepartment(id);
    }



    @PostMapping(value = "/departments/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Department updateDepartment(@PathVariable UUID id, @RequestBody DepartmentDTO department) {
        departmentService.updateDepartment(department,id);
        return null;
    }
}
