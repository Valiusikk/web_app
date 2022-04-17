package com.example.demo.service;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class DeparmentServiceImpl{

    private final DepartmentDao repository;

    public List<Department> getAllDepartments() {
        List<Department> departmentList = new ArrayList<>();
        repository.findAll().forEach(departmentList::add);
        return departmentList;
    }

    public Department getDepartment(String id) {
        return repository.findById(id).get();
    }

    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    public boolean updateDepartment(String id) {
        return false;
    }
}
