package com.example.demo.powermock;

import com.example.demo.controller.EmployeeController;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.example.demo.controller.*")
class EmployeeControllerTest {

    private static final EmployeeDTO EMPLOYEE =
            new EmployeeDTO("Alex","Smith","123456",1547,"email",new DepartmentDTO());

    private EmployeeController controller;

    private EmployeeService service =mock(EmployeeServiceImpl.class);

    private EmployeeController controllerMock;

    @BeforeEach
    void setUp() {
        spy(service);
        controller = new EmployeeController(service);
        controllerMock = spy(controller);
    }

    @Test
    void showAllEmployees() {
        //given
        List<EmployeeDTO> list = new ArrayList<>();
        list.add(EMPLOYEE);
        when(service.getAllEmployees()).thenReturn(list);

        //when
        List<EmployeeDTO> actualList= controller.showAllEmployees();

        //then
        assertEquals(list,actualList,"Should return list of all employees from db");
    }

    @Test
    void getEmployee() {
    }

    @Test
    void addNewEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}