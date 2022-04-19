package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.UserExistsException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl {

    private final EmployeeRepository repository;

    private final DepartmentServiceImpl departmentService;

    private final DepartmentRepository departmentRepository;


    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return repository.findAll().stream().map(EmployeeDTO::fromEmployee).collect(Collectors.toList());
    }

    private Employee createEmployee(EmployeeDTO employeeDTO) {
        final Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getEmployeeName());
        newEmployee.setEmail(employeeDTO.getEmail());
        newEmployee.setSurname(employeeDTO.getEmployeeSurname());
        newEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        newEmployee.setSalary(employeeDTO.getSalary());
        return newEmployee;
    }


    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        final Optional<Employee> newEmployee = repository.findByEmail(employeeDTO.getEmail());
        if (newEmployee.isPresent()) {
            throw new UserExistsException("Employee with email " + employeeDTO.getEmail() + " is already saved");
        }
        final Employee employee = createEmployee(employeeDTO);
        final Optional<Department> department = departmentRepository.findByDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName());
        if (department.isEmpty()) {
            departmentService.addDepartment(employeeDTO.getDepartmentDto());
        }
        employee.setDepartmentId(departmentRepository.findByDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName()).get());
        repository.save(employee);
        return EmployeeDTO.fromEmployee(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployee(UUID id) {
        final Employee employee = repository.findById(id.toString()).orElseThrow();
        return EmployeeDTO.fromEmployee(employee);
    }

    public EmployeeDTO deleteEmployee(String email) {
        final Employee employee = repository.findByEmail(email).orElseThrow();
        repository.delete(employee);
        return EmployeeDTO.fromEmployee(employee);
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        final Employee employee = repository.findByEmail(employeeDTO.getEmail()).orElseThrow(() -> new EmployeeNotFoundException("Employee with email " + employeeDTO.getEmail() + " not found"));
        final Department department = new Department();
        department.setDepartmentId(employeeDTO.getEmployeeName().substring(0, 5));
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
