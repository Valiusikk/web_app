package com.example.demo.mockmvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.controller.DepartmentController;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.service.DepartmentService;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcDepartmentControllerText {

    private static final DepartmentDTO DEPARTMENT_DTO =
            new DepartmentDTO("NAME","LOCATION");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentController controller;

    @Autowired
    private DepartmentService service;

    @BeforeEach
    void setUp() {
        controller = new DepartmentController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getAllDepartments() throws Exception {
        //given
        final String expected = "[{\"departmentName\":\"Info\",\"location\":\"Romania\"},{\"departmentName\":\"Info Tech\",\"location\":\"Moldova\"}]";

        //then
        this.mockMvc.perform(get("/api/departments")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }

    @Test
    void getDepartmentByNameShouldReturnDTO() throws Exception {
        //given
        final String expected = "{\"departmentName\":\"Info\",\"location\":\"Romania\"}";
        final String departmentName = "Info";
        //then
        this.mockMvc.perform(get("/api/departments"+departmentName)).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().json(expected));

    }
}
