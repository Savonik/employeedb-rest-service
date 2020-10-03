package com.savonik.employeedb.rest;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeController employeeController;

    private List<Employee> testEmployees;
    private Employee testEmployee;

    @Before
    public void beforeTest() {
        testEmployees = Arrays.asList(
                new Employee(1L, "Ivan", "Ivanov",
                        1L, "programmer", Gender.MALE, new Date()),
                new Employee(2L, "Stepan", "Stepanov",
                        2L, "data engineer", Gender.MALE, new Date()));

        testEmployee = new Employee(5L, "Arsen", "Taliev",
                3L, "data engineer", Gender.MALE, new Date());
    }

    @Test
    public void controllerLoads() {
        assertThat(employeeController).isNotNull();
    }

    @Test
    public void canGetByIdWhenExists() throws Exception {
        given(employeeDao.findById(1L)).willReturn(testEmployees);

        MockHttpServletResponse response = mvc.perform(
                get("/employees/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       // assertThat(response.getContentAsString()).isNotEmpty();
    }

    @Test
    public void canGetByIdWhenDoesNotExist() throws Exception {
        given(employeeDao.findById(0L))
                .willReturn(null);

        MockHttpServletResponse response = mvc.perform(
                get("/employees/0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.getContentAsString()).isNotEmpty();
    }
}
