package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.exception.UserExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl{

    private final EmployeeRepository repository;

    private final DepartmentServiceImpl departmentService;

    private final DepartmentRepository departmentRepository;


    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream()
                .map(EmployeeDTO::fromEmployee)
                .collect(Collectors.toList());
    }

    private Employee createEmployee(EmployeeDTO employeeDTO){
        Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getEmployeeName());
        newEmployee.setEmail(employeeDTO.getEmail());
        newEmployee.setSurname(employeeDTO.getEmployeeSurname());
        newEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        newEmployee.setSalary(employeeDTO.getSalary());
        return newEmployee;
    }


    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee newEmployee = repository.findByEmail(employeeDTO.getEmail()).orElseThrow(()->{
            throw new UserExistsException("User with email "+employeeDTO.getEmail()+" already exists");
        });

        if(Objects.nonNull(departmentRepository.findByDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName()))){
            Department department = new Department();
            department.setDepartmentId(employeeDTO.getDepartmentDto().getDepartmentName().substring(0,5));
            department.setDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName());
            department.setLocation(employeeDTO.getDepartmentDto().getLocation());
            newEmployee.setDepartmentId(department);
        }
        departmentService.addDepartment(employeeDTO.getDepartmentDto());


        repository.save(newEmployee);
        return employeeDTO;
    }

    public EmployeeDTO getEmployee(UUID id) {
        Employee employee = repository.findById(id.toString()).orElseThrow();
        return EmployeeDTO.fromEmployee(employee);
    }

    public EmployeeDTO deleteEmployee(String email) {
        Employee employee = repository.findByEmail(email).orElseThrow();
        repository.delete(employee);
        return EmployeeDTO.fromEmployee(employee);
    }
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO){
        Employee employee = repository.findByEmail(employeeDTO.getEmail()).orElseThrow(()-> new UserNotFoundException());

        Department department = new Department();
        department.setDepartmentId(employeeDTO.getEmployeeName().substring(0,5));
        department.setDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName());
        department.setLocation(employeeDTO.getDepartmentDto().getLocation());
        employee.setName(employeeDTO.getEmployeeName());
        employee.setSurname(employeeDTO.getEmployeeSurname());
        employee.setSalary(employeeDTO.getSalary());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setDepartmentId(department);
        return employeeDTO;
    }
}
