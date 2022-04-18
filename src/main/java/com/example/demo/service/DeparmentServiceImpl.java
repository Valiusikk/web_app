package com.example.demo.service;

import com.example.demo.repository.DepartmentRepository;
import com.example.demo.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class DeparmentServiceImpl{

    private final DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        repository.findAll().forEach(departmentList::add);
        return departmentList;
    }

    public Department getDepartment(String id) {
        return repository.findById(id).orElseThrow();
    }

    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    public boolean updateDepartment(String id) {
        return false;
    }
}
