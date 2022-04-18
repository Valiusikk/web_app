package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.entity.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl {

    private final DepartmentRepository repository;

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = repository.findAll();
        List<DepartmentDTO> employeeDTOS = new ArrayList<>();
        for(Department department:departments){
            employeeDTOS.add(DepartmentDTO.fromDepartment(department));
        }
        return employeeDTOS;
    }

    public DepartmentDTO getDepartment(UUID id) {
        Department department = repository.findById(id.toString()).orElseThrow();
        return new DepartmentDTO(department.getDepartmentName(),department.getLocation());
    }

    public Department addDepartment(Department department) {
        return repository.save(department);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO,UUID id){
        Department department = repository.findById(id.toString()).orElseThrow(()-> new EmployeeNotFoundException());
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocation(departmentDTO.getLocation());
        return  departmentDTO;
    }
}
