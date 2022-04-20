package com.example.demo.service;

import com.example.demo.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    /**
     * Should return existing employee, which email is equal to email passed in method
     * If there is no one employee with such email, throws WebAppException
     *
     * @param email email of employee, that is unique value
     * @return EmployeeDTO that contains all informative fields from employee object
     */
    EmployeeDTO getEmployee(String email);

    /**
     * Should delete from db existing employee, which email is equal to email passed in method
     * If in db is no one employee with such email, throws WebAppException
     *
     * @param email email of employee, that is unique value
     * @return EmployeeDTO - representation of employee object,
     * that contains all informative fields from employee object
     */
    EmployeeDTO deleteEmployee(String email);

    /**
     * Should update employee with email, that is equal to email passed in method
     * If in db is no one employee with such email, throws WebAppException
     *
     * @param employeeDTO EmployeeDTO - representation of employee object,
     *                    that contains all informative fields from employee object
     * @return EmployeeDTO of saved employee
     */
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO);

    /**
     * Creates and saves in database new department in case, when there is no any department with such name
     * If there already is saved employee with email or phone number from employeeDTO, throws WebAppException
     *
     * @param employeeDTO employeeDTO of new employee
     * @return employeeDTO of saved employee, in case when update complete
     */
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    /**
     * Should return List of all existing employees in DB
     * Otherwise returns empty list if there are not any employee at all
     *
     * @return List of employeeDTO's for all employees or empty list if there are not any employee at all
     */
    List<EmployeeDTO> getAllEmployees();

}
