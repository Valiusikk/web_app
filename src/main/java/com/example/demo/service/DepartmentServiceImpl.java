package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.entity.Department;
import com.example.demo.service.execution.AbstractValidator;
import com.example.demo.service.execution.DepartmentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    private final AbstractValidator validator;

    public List<DepartmentDTO> getAllDepartments() {
        log.info("Getting all employees");
        return repository.findAll().stream().map(DepartmentDTO::fromDepartment).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartment(String name) {
        log.info("Getting all department with name " + name);
        validator.validateIfExists(name);
        return DepartmentDTO.fromDepartment(repository.findByDepartmentName(name).get());
    }

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        validator.validateIfExists(departmentDTO.getDepartmentName());
        final Department newDepartment = new Department();
        newDepartment.setDepartmentId(departmentDTO.getDepartmentName().substring(0, 5));
        newDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        newDepartment.setLocation(departmentDTO.getLocation());
        repository.save(newDepartment);
        log.info("Department with name = " + departmentDTO.getDepartmentName() + " was succesfully saved");
        return departmentDTO;
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        validator.validateIfDoesntExists(departmentDTO.getDepartmentName());
        final Department department = repository.findByDepartmentName(departmentDTO.getDepartmentName()).get();
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocation(departmentDTO.getLocation());
        log.info("Department with name = " + departmentDTO.getDepartmentName() + " was succesfully updated");
        return departmentDTO;
    }

    public Optional<Department> findByDepartmentName(String departmentName) {
        return repository.findByDepartmentName(departmentName);
    }
}
