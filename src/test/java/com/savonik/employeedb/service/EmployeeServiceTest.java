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

        List<Employee> returnedEmployees = (List<Employee>) employeeService.getAll();
        assertThat(returnedEmployees).isEqualTo(employeeList);

        Mockito.verify(employeeDao).findAll();
    }

    @Test
    public void findAllWhenTableIsEmptyTest() {
        Mockito.when(employeeDao.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<Employee> returnedEmployees = (List<Employee>) employeeService.getAll();
        assertThat(returnedEmployees).isEqualTo(Collections.EMPTY_LIST);

        Mockito.verify(employeeDao).findAll();
    }

    @Test
    public void findByIdTest() {
        Mockito.when(employeeDao.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> returnedEmployee = employeeService.getById(1L);
        assertThat(returnedEmployee).isEqualTo(employee);

        Mockito.verify(employeeDao).findById(1L);
    }

    @Test
    public void findByIdWhenIdDoesNotExistTest() {
        Mockito.when(employeeDao.findById(0L)).thenReturn(null);

        Optional<Employee> returnedEmployee = employeeService.getById(0L);
        assertThat(returnedEmployee).isEqualTo(null);

        Mockito.verify(employeeDao).findById(1L);
    }

}
