package com.example.demo.controller;

import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.EmployeeService;
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
class EmployeeControllerTest {

    private static final EmployeeDTO EMPLOYEE =
            new EmployeeDTO("Alex","Smith","123456",1547,"email",new DepartmentDTO());


    @InjectMocks
    private EmployeeController controller;

    @Mock
    private EmployeeService service;

    @Test
    void showAllEmployeesSucceeds() {
        //given
        final List<EmployeeDTO> expectedList = new ArrayList<>();
        expectedList.add(EMPLOYEE);
        when(service.getAllEmployees()).thenReturn(expectedList);

        //when
        final List<EmployeeDTO> actualList= controller.showAllEmployees();

        //then
        assertEquals(expectedList,actualList,"getAllEmployees should return list of all employees saved in database");
    }

    @Test
    void getEmployee() {
        //given
        when(service.getEmployee(EMPLOYEE.getEmail())).thenReturn(EMPLOYEE);

        //when
        final EmployeeDTO actual = controller.getEmployee(EMPLOYEE.getEmail());

        //then
        assertEquals(EMPLOYEE,actual,
                "If in database exists employee with email passed to method, I should get that employee");
    }

    @Test
    void addNewEmployee() {
        //given
        when(service.saveEmployee(EMPLOYEE)).thenReturn(EMPLOYEE);

        //when
        final  EmployeeDTO actual = controller.addNewEmployee(EMPLOYEE);

        //then
        assertEquals(EMPLOYEE,actual,"Should return DTO that was saved in database");
    }

    @Test
    void updateEmployee() {
        //given
        when(service.updateEmployee(EMPLOYEE)).thenReturn(EMPLOYEE);

        //when
        final  EmployeeDTO actual = controller.updateEmployee(EMPLOYEE);

        //then
        assertEquals(EMPLOYEE,actual,"Should return DTO that was updated");
    }

    @Test
    void deleteEmployee(){
        //given
        when(service.deleteEmployee(EMPLOYEE.getEmail())).thenReturn(EMPLOYEE);

        //when
        final  EmployeeDTO actual = controller.deleteEmployee(EMPLOYEE.getEmail());

        //then
        assertEquals(EMPLOYEE,actual,"Should return DTO that was deleted from database");
    }
}