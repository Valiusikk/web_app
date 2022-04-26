package com.example.demo.service;

import com.example.demo.dto.DepartmentDTO;

import java.util.List;

public interface DepartmentService {
    /**
     * Should return List of all existing departments in DB
     * Otherwise returns empty list if there are not any department at all
     *
     * @return List of all departments or empty list if there are not any department at all
     */
    List<DepartmentDTO> getAllDepartments();

    /**
     * Should return department, that id equals to id of any existing department
     * Otherwise throws WebAppException
     *
     * @param name name of department
     * @return DepartmentDTO of found department
     */
    DepartmentDTO getDepartment(String name);

    /**
     * Creates and saves in database new department in case, when there is no any department with such name
     * Otherwise throws WebAppException
     *
     * @param departmentDTO DepartmentDTO of new department
     * @return DepartmentDTO of saved department
     */
    DepartmentDTO saveDepartment(DepartmentDTO departmentDTO);

    /**
     * Should find and update existing department, which name is equal with name of DepartmentDTO passed in method
     * If there is no department with such name, throws WebAppException
     *
     * @param departmentDTO representation of department that user is going to update
     * @return DepartmentDTO that was updated
     */
    DepartmentDTO updateDepartment(DepartmentDTO departmentDTO);
}
