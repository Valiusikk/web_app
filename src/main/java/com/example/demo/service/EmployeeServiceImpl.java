package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl{

    private final EmployeeRepository repository;

    private final DepartmentServiceImpl departmentService;


    @Transactional
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(Employee employee:employees){
            employeeDTOS.add(EmployeeDTO.fromEmployee(employee));
        }
        return employeeDTOS;
    }

    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setEmail(employeeDTO.getEmail());
        employee.setName(employeeDTO.getEmployeeName());


        repository.save(employee);
    }

    public EmployeeDTO getEmployee(UUID id) {
        Employee employee = repository.findById(id.toString()).orElseThrow();
        return EmployeeDTO.fromEmployee(employee);
    }

    public void deleteEmployee(UUID id) {
        Employee employee = repository.findById(id.toString()).orElseThrow();
        repository.delete(employee);
    }
}
