package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entity.Department;
import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.exception.WebAppException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entity.Employee;
import com.example.demo.service.execution.CreateEmployeeValidator;
import com.example.demo.service.execution.UpdateEmployeeValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    public static final String EMPLOYEE_NAME = "Employee";

    private final EmployeeRepository repository;

    private final DepartmentServiceImpl departmentService;

    private UpdateEmployeeValidator updateValidator;

    private CreateEmployeeValidator createValidator;

    @PostConstruct
    void init(){
        updateValidator = new UpdateEmployeeValidator(repository);

        createValidator = new CreateEmployeeValidator(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Getting list of all employees");
        return repository.findAll().stream().map(EmployeeDTO::fromEmployee).collect(Collectors.toList());
    }

    private Employee createEmployee(EmployeeDTO employeeDTO) {
        final Employee newEmployee = new Employee();
        newEmployee.setName(employeeDTO.getEmployeeName());
        newEmployee.setEmail(employeeDTO.getEmail());
        newEmployee.setSurname(employeeDTO.getEmployeeSurname());
        newEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
        newEmployee.setSalary(employeeDTO.getSalary());
        log.info("Employee with email " + employeeDTO.getEmail() + " was updated");
        return newEmployee;
    }

    private boolean checkForPhoneNumber(String phoneNumber) {
        return getAllEmployees().stream()
                .map(EmployeeDTO::getPhoneNumber)
                .anyMatch(phone -> phone.equals(phoneNumber));
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        createValidator.validate(employeeDTO.getEmail());
        if (checkForPhoneNumber(employeeDTO.getPhoneNumber())) {
            log.error("Employee already exist and can't be saved");
            throw new EntityAlreadyExistsException(EMPLOYEE_NAME, "phone number", employeeDTO.getPhoneNumber());
        }
        final Employee employee = createEmployee(employeeDTO);
        final Optional<Department> department = departmentService.findByDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName());
        if (department.isEmpty()) {
            departmentService.saveDepartment(employeeDTO.getDepartmentDto());
        }
        employee.setDepartmentId(departmentService.findByDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName()).get());
        repository.save(employee);
        log.info("New Employee with email" + employee.getEmail() + " was saved");
        return EmployeeDTO.fromEmployee(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployee(String email) {
        Employee employee = repository.findByEmail(email).get();
        log.info("Getting employee with email " + email);
        return EmployeeDTO.fromEmployee(employee);
    }

    @Override
    public EmployeeDTO deleteEmployee(String email) {

        Employee employee = repository.findByEmail(email).get();
        repository.delete(employee);
        log.info("Employee with email " + email + " was deleted");
        return EmployeeDTO.fromEmployee(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) {
        updateValidator.validate(employeeDTO.getEmail());
        final Employee employee = new Employee();
        final Department department = new Department();
        department.setDepartmentId(employeeDTO.getEmployeeName().substring(0, 5));
        department.setDepartmentName(employeeDTO.getDepartmentDto().getDepartmentName());
        department.setLocation(employeeDTO.getDepartmentDto().getLocation());
        employee.setName(employeeDTO.getEmployeeName());
        employee.setSurname(employeeDTO.getEmployeeSurname());
        employee.setSalary(employeeDTO.getSalary());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setDepartmentId(department);
        log.info("Employee with email " + employeeDTO.getEmail() + " was updated");
        return employeeDTO;
    }
}
