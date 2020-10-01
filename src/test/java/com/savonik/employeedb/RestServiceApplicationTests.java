package com.savonik.employeedb;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import com.savonik.employeedb.rest.EmployeeController;
import com.savonik.employeedb.rest.RestResponse;
import jdk.management.resource.ResourceRequestDeniedException;
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

import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceApplicationTests {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void controllerLoads() {
        assertThat(employeeController).isNotNull();
    }

    @Test
    public void canGetByIdWhenExists() throws Exception {
        given(employeeDao.findById(1L))
                .willReturn(new Employee(1L, "Ivan", "Ivanov",
                        1L, "engineer", Gender.MALE,
                        new SimpleDateFormat("yyyy-MM-dd").parse("2000-10-10")));

        MockHttpServletResponse response = mvc.perform(
                get("/employees/1").accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void canRetrieveByIdWhenDoesNotExist() throws Exception {
        given(employeeDao.findById(0L))
                .willReturn(null);

        MockHttpServletResponse response = mvc.perform(
                get("/employees/0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.equals(new RestResponse(ERROR_STATUS)));
    }
}
