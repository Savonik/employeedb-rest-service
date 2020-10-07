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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final List<Employee> employeeList = Arrays.asList(
            new Employee(1L, "Ivan", "Ivanov",
                    1L, "programmer", Gender.MALE, DATE_FORMAT.parse("2000-10-10")),
            new Employee(2L, "Stepan", "Stepanov",
                    2L, "data engineer", Gender.MALE, DATE_FORMAT.parse("1999-12-12")),
            new Employee(3L, "Vitold", "Ravkov",
                    2L, "marketer", Gender.MALE, DATE_FORMAT.parse("1999-10-12")));
    private final Employee employee = new Employee(3L, "Alex", "Alexin",
            2L, "engineer", Gender.MALE, DATE_FORMAT.parse("1999-10-12"));

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService employeeService;

    public EmployeeServiceTest() throws ParseException {
    }

    @Test
    public void findAllTest() {
        Mockito.when(employeeDao.findAll()).thenReturn(employeeList);

        List<Employee> returnedEmployees = employeeService.findAll();
        assertThat(returnedEmployees).isEqualTo(employeeList);

        Mockito.verify(employeeDao).findAll();
    }

    @Test
    public void findAllWhenDoesNotExistTest() {
        Mockito.when(employeeDao.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<Employee> returnedEmployees = employeeService.findAll();
        assertThat(returnedEmployees).isEqualTo(Collections.EMPTY_LIST);

        Mockito.verify(employeeDao).findAll();
    }

    @Test
    public void findByIdTest() {
        Mockito.when(employeeDao.findById(1L)).thenReturn(employeeList);

        List<Employee> returnedEmployee = employeeService.findById(1L);
        assertThat(returnedEmployee).isEqualTo(employeeList);

        Mockito.verify(employeeDao).findById(1L);
    }

    @Test
    public void findByIdWhenDoesNotExistTest() {
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
    public void deleteEmployeeWhenDoesNotExistTest() {
        Mockito.when(employeeDao.deleteEmployee(1L)).thenReturn(0);

        int deleteStatus = employeeService.deleteEmployee(1L);
        assertThat(deleteStatus).isEqualTo(0);

        Mockito.verify(employeeDao).deleteEmployee(1L);
    }

    @Test
    public void updateEmployeeTest() {
        Mockito.when(employeeDao.updateEmployee(employee, 1L)).thenReturn(1);

        int updateStatus = employeeService.updateEmployee(employee, 1L);
        assertThat(updateStatus).isEqualTo(1);

        Mockito.verify(employeeDao).updateEmployee(employee, 1L);
    }

    @Test
    public void updateEmployeeWhenDoesNotExistTest() {
        Mockito.when(employeeDao.updateEmployee(employee, 1L)).thenReturn(0);

        int updateStatus = employeeService.updateEmployee(employee, 1L);
        assertThat(updateStatus).isEqualTo(0);

        Mockito.verify(employeeDao).updateEmployee(employee, 1L);
    }

}
