package com.example.demo.powermock;

import com.example.demo.controller.DepartmentController;
import com.example.demo.dto.DepartmentDTO;
import com.example.demo.exception.EntityAlreadyExistsException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "com.example.demo.controller.*")
class DepartmentControllerTest {

    private DepartmentController departmentControllerMock;

    private DepartmentServiceImpl departmentServiceMock = mock(DepartmentServiceImpl.class);

    private static final DepartmentDTO DEPARTMENT_DTO = new DepartmentDTO("NAME", "LOCATION");

    @BeforeEach
    void setUp() throws Exception {
        whenNew(DepartmentServiceImpl.class).withArguments(DepartmentRepository.class).
                thenReturn(departmentServiceMock);
        departmentControllerMock = new DepartmentController(departmentServiceMock);
    }

    @Test
    void saveDepartmentSucceeds() {
        //given
        when(departmentServiceMock.saveDepartment(DEPARTMENT_DTO)).thenReturn(DEPARTMENT_DTO);

        //when
        final DepartmentDTO departmentDTO = departmentControllerMock.saveDepartment(DEPARTMENT_DTO);

        //then
        verify(departmentServiceMock).saveDepartment(DEPARTMENT_DTO);
        assertEquals(DEPARTMENT_DTO, departmentDTO, "Department controller must return DTO that it saved");
    }

    @Test
    void saveDepartmentIfItExistsShouldThrow() {
        //given
        final String className = "Department";

        final String specifiedName = "Department name";

        when(departmentServiceMock.saveDepartment(DEPARTMENT_DTO)).
                thenThrow(new EntityAlreadyExistsException(className,specifiedName, DEPARTMENT_DTO.getDepartmentName()));

        //when
        final  EntityAlreadyExistsException exception =
                assertThrows(EntityAlreadyExistsException.class,()->departmentServiceMock.saveDepartment(DEPARTMENT_DTO));

        //then
        assertEquals("Department with Department name "+DEPARTMENT_DTO.getDepartmentName()+" is already in database",exception.getDetails().getMessage(),"");
    }

//    @Test
//    void showAllDepartments() {
//    }
//
//    @Test
//    void getDepartment() {
//    }
//
//    @Test
//    void updateDepartment() {
//    }
}