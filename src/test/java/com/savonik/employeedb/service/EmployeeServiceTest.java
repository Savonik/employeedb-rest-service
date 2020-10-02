package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void findAllTest() {
        List<Employee> returnedEmployees = new LinkedList<>();
        returnedEmployees.add(new Employee(1L, "Ivan", "Ivanov",
                1L, "programmer", Gender.FEMALE, new Date()));

        Mockito.when(employeeDao.findAll()).thenReturn(returnedEmployees);

        List<Employee> employees = employeeService.findAll();
        assertThat(employees).isEqualTo(returnedEmployees);

        Mockito.verify(employeeDao).findAll();
    }
}
