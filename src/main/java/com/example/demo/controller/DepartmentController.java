package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping(value = "/departments")
    public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO department) {
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> showAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{name}")
    public DepartmentDTO getDepartment(@PathVariable String name) {
        return departmentService.getDepartment(name);
    }

    @PatchMapping(value = "/departments")
    public DepartmentDTO updateDepartment(@Valid @RequestBody DepartmentDTO department) {
        return departmentService.updateDepartment(department);
    }
}
