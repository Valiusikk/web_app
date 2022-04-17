package com.example.demo.service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl{

    private final EmployeeDAO repository;


    @Transactional
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        repository.findAll().forEach(employees::add);
        return employees;
    }

    public void saveEmployee(Employee employee) {
        repository.save(employee);
    }

    public Employee getEmployee(String id) {
        return repository.findById(id).get();
    }

    public void deleteEmployee(String id) {
        repository.delete(getEmployee(id));
    }
}
