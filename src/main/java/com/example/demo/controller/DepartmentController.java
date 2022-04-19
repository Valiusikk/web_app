package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PostMapping(value = "/departments")
    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO department) {
        return departmentService.addDepartment(department);
    }

    @RequestMapping("/departments")
    public List<DepartmentDTO> showAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public DepartmentDTO getDepartment(@PathVariable String id) {
        return departmentService.getDepartment(id);
    }

    @PatchMapping(value = "/departments/{id}")
    public DepartmentDTO updateDepartment(@PathVariable String id, @RequestBody DepartmentDTO department) {
        return departmentService.updateDepartment(department,id);
    }
}
