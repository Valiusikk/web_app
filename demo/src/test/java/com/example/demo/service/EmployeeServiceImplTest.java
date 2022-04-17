package com.example.demo.service;

import com.example.demo.dao.EmployeeDAO;
import com.example.demo.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
//
//    @Mock
//    private EmployeeDAO dao;
//
//    @InjectMocks
//    private EmployeeServiceImpl employeeService;
//
//    @Captor
//    private ArgumentCaptor<Integer> integerArgumentCaptor;
//
//    @Test
//    void shouldReturnEmptyCollectionOnGetEmployees(){
//        // given
   //     List<Object> expectedResult = Collections.emptyList();

//        // when
//        List<Employee> actualResult = employeeService.getAllEmployees();
//
//        // then
//        assertEquals(expectedResult, actualResult, "Should be equal");
//    }
//
//    @Test
//    void shouldReturnCollectionOnGetEmployees(){
//        // given
//        Employee employee = new Employee("11", "Vaneok", "VaneokSr", "eee@mail.ru", 1, "Uou");
//        List<Employee> expectedResult = Collections.singletonList(employee);
//        when(dao.getAllEmployees()).thenReturn(expectedResult);
//        // when
//        List<Employee> actualResult = employeeService.getAllEmployees();
//
//        // then
//        assertEquals(expectedResult, actualResult, "Should be equal");
////    }
//
//    @Test
//    void test(){
//        employeeService.getEmployee(111);
//        when(dao.getEmployee(111)).thenReturn(new Employee());
//        verify(dao).getEmployee(integerArgumentCaptor.capture());
//        System.out.println();
//    }
}