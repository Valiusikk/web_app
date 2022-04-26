package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.entity.Department;
import com.example.demo.service.execution.CreateDepartmentValidator;
import com.example.demo.service.execution.UpdateDepartmentValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    private UpdateDepartmentValidator updateValidator;

    private CreateDepartmentValidator createValidator;

    @PostConstruct
    void init(){
        updateValidator = new UpdateDepartmentValidator(repository);

        createValidator = new CreateDepartmentValidator(repository);
    }

    public List<DepartmentDTO> getAllDepartments() {
        log.info("Getting all employees");
        return repository.findAll().stream().map(DepartmentDTO::fromDepartment).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartment(String name) {
        log.info("Getting all department with name " + name);
        return DepartmentDTO.fromDepartment(repository.findByDepartmentName(name).get());
    }

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {
        createValidator.validate(departmentDTO.getDepartmentName());
        final Department newDepartment = new Department();
        newDepartment.setDepartmentId(departmentDTO.getDepartmentName().substring(0, 5));
        newDepartment.setDepartmentName(departmentDTO.getDepartmentName());
        newDepartment.setLocation(departmentDTO.getLocation());
        repository.save(newDepartment);
        log.info("Department with name = " + departmentDTO.getDepartmentName() + " was successfully saved");
        return departmentDTO;
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO) {
        updateValidator.validate(departmentDTO.getDepartmentName());
        final Department department = repository.findByDepartmentName(departmentDTO.getDepartmentName()).get();
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setLocation(departmentDTO.getLocation());
        log.info("Department with name = " + departmentDTO.getDepartmentName() + " was successfully updated");
        return departmentDTO;
    }

    public Optional<Department> findByDepartmentName(String departmentName) {
        return repository.findByDepartmentName(departmentName);
    }
}
