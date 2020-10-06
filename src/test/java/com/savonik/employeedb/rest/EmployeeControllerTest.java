package com.savonik.employeedb.rest;

import com.savonik.employeedb.dao.EmployeeDao;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private EmployeeDao employeeDao;

    @BeforeEach
    public void beforeTest() throws IOException {
        employeeDao.query(new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")), StandardCharsets.UTF_8));
        employeeDao.query(new String(Files.readAllBytes(Paths.get("src/main/resources/data.sql")), StandardCharsets.UTF_8));
    }

    @Test
    public void controllerLoads() {
        assertThat(employeeController).isNotNull();
    }

    @Test
    public void getByIdTest() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();

        assertThat(response.getContentAsString()).isEqualTo(
                "[{\"employeeId\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"departmentId\":1,\"jobTitle\":\"engineer\",\"gender\":\"MALE\",\"dateOfBirth\":\"2000-10-09T21:00:00.000+00:00\"}]");
    }

    @Test
    public void canGetByIdWhenDoesNotExist() throws Exception {
        MockHttpServletResponse response = mvc.perform(get("/employees/0")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isNotEmpty();
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }
}
