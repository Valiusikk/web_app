package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl {

    private final DepartmentRepository repository;

    public List<DepartmentDTO> getAllDepartments() {
        return repository.findAll().stream().map(DepartmentDTO::fromDepartment).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartment(String id) {
        return DepartmentDTO.fromDepartment(repository.findById(id).orElseThrow());
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) {
        final Department department = new Department();
        department.setDepartmentId(departmentDTO.getDepartmentName().substring(0, 5));
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocation(departmentDTO.getLocation());
        repository.save(department);
        return departmentDTO;
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, String id) {
        final Department department = repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocation(departmentDTO.getLocation());
        return departmentDTO;
    }
}
