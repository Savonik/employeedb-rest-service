package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

//mochitorunner
//@RunWith(Mockito.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void findAllTest(){

        Mockito.when(employeeDao.findAll()).thenReturn(Collections.emptyList());
        List<Employee> employees = employeeService.findAll();

        Mockito.verify(employeeDao).findAll();

    }
}
