package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    private static final DepartmentDTO DEPARTMENT_DTO = new DepartmentDTO("NAME","LOCATION");

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;


    @Test
    void addDepartmentSucceeds() {
        //given
        when(departmentService.saveDepartment(DEPARTMENT_DTO)).thenReturn(DEPARTMENT_DTO);

        //when
        final DepartmentDTO expected= departmentController.saveDepartment(DEPARTMENT_DTO);

        //then
        assertEquals(expected,DEPARTMENT_DTO,"Department controller must return DTO that it saved");

    }

    @Test
    void showAllDepartmentsSucceeds() {
        //given
        final ArrayList<DepartmentDTO> expectedList = new ArrayList();
        expectedList.add(DEPARTMENT_DTO);
        when(departmentService.getAllDepartments()).thenReturn(expectedList);

        //when
        final List<DepartmentDTO> actualList= departmentController.showAllDepartments();

        //then
        assertEquals(expectedList,actualList,"Should return list of employees saved in database");
    }

    @Test
    void getDepartmentSucceeds() {
        //given
        when(departmentService.getDepartment(DEPARTMENT_DTO.getDepartmentName())).thenReturn(DEPARTMENT_DTO);

        //when
        final DepartmentDTO expected = departmentController.getDepartment(DEPARTMENT_DTO.getDepartmentName());

        //then
        assertEquals(expected,DEPARTMENT_DTO,
                "If i pass department name to this method, I should get department with this name");

    }

    @Test
    void updateDepartmentSucceeds() {
        //given
        when(departmentService.updateDepartment(DEPARTMENT_DTO)).thenReturn(DEPARTMENT_DTO);

        //when
        final DepartmentDTO expected = departmentController.updateDepartment(DEPARTMENT_DTO);

        //then
        assertEquals(expected,DEPARTMENT_DTO,"Should return Department DTO that was updated");
    }
}