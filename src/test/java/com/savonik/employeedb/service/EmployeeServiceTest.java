package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import org.junit.Before;
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
    public void findAllTest() {
        Mockito.when(employeeDao.findAll()).thenReturn(testEmployees);

        List<Employee> returnedEmployees = employeeService.findAll();
        assertThat(returnedEmployees).isEqualTo(testEmployees);

        Mockito.verify(employeeDao).findAll();
    }

    @Test
    public void findByIdTest() {
        Mockito.when(employeeDao.findById(1L)).thenReturn(testEmployees);

        List<Employee> returnedEmployee = employeeService.findById(1L);
        assertThat(returnedEmployee).isEqualTo(testEmployees);

        Mockito.verify(employeeDao).findById(1L);
    }

    @Test
    public void NotFindByIdTest() {
        Mockito.when(employeeDao.findById(1L)).thenReturn(Collections.emptyList());

        List<Employee> returnedEmployee = employeeService.findById(1L);
        assertThat(returnedEmployee).isEqualTo(Collections.emptyList());

        Mockito.verify(employeeDao).findById(1L);
    }

    @Test
    public void deleteEmployeeTest() {
        Mockito.when(employeeDao.deleteEmployee(1L)).thenReturn(1);

        int deleteStatus = employeeService.deleteEmployee(1L);
        assertThat(deleteStatus).isEqualTo(1);

        Mockito.verify(employeeDao).deleteEmployee(1L);
    }

    @Test
    public void updateEmployeeTest() {
        Mockito.when(employeeDao.updateEmployee(testEmployee, 1L)).thenReturn(1);

        int updateStatus = employeeService.updateEmployee(testEmployee, 1L);
        assertThat(updateStatus).isEqualTo(1);

        Mockito.verify(employeeDao).updateEmployee(testEmployee, 1L);
    }

    @Test
    public void NotUpdateEmployeeTest() {
        Mockito.when(employeeDao.updateEmployee(testEmployee, 1L)).thenReturn(0);

        int updateStatus = employeeService.updateEmployee(testEmployee, 1L);
        assertThat(updateStatus).isEqualTo(0);

        Mockito.verify(employeeDao).updateEmployee(testEmployee, 1L);
    }

}
