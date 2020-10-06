package com.savonik.employeedb.dao;

import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    private List<Employee> testEmployees;
    private Employee employee;

    @BeforeEach
    public void beforeTest() throws ParseException, IOException {
        prepareTestTable();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        testEmployees = Arrays.asList(
                new Employee(1L, "Ivan", "Ivanov",
                        1L, "programmer", Gender.MALE, dateFormat.parse("2000-10-10")),
                new Employee(2L, "Stepan", "Stepanov",
                        2L, "data engineer", Gender.MALE, dateFormat.parse("1999-12-12")),
                new Employee(3L, "Vitold", "Ravkov",
                        2L, "marketer", Gender.MALE, dateFormat.parse("1999-10-12")));

        employee = new Employee(3L, "Alex", "Alexin",
                2L, "engineer", Gender.MALE, dateFormat.parse("1999-10-12"));
    }

    private void prepareTestTable() throws IOException {
        employeeDao.query(new String(Files.readAllBytes(Paths.get("src/main/resources/schema.sql")), StandardCharsets.UTF_8));
        employeeDao.query(new String(Files.readAllBytes(Paths.get("src/main/resources/data.sql")), StandardCharsets.UTF_8));
    }

    @Test
    public void findAllTest() {
        List<Employee> returnedEmployees = employeeDao.findAll();

        assertThat(returnedEmployees).hasSize(3);
        assertThat(returnedEmployees.get(0).getEmployeeId()).isEqualTo(testEmployees.get(0).getEmployeeId());
        assertThat(returnedEmployees.get(1).getFirstName()).isEqualTo(testEmployees.get(1).getFirstName());
        assertThat(returnedEmployees.get(2).getGender()).isEqualTo(testEmployees.get(2).getGender());
    }

    @Test
    public void findByIdTest() {
        List<Employee> returnedEmployees = employeeDao.findById(2L);

        assertThat(returnedEmployees).hasSize(1);
        assertThat(returnedEmployees.get(0).getEmployeeId()).isEqualTo(testEmployees.get(1).getEmployeeId());
    }

    @Test
    public void notFindByIdTest() {
        List<Employee> returnedEmployees = employeeDao.findById(5L);

        assertThat(returnedEmployees).hasSize(0);
    }

    @Test
    public void addEmployeeTest() throws IOException {
        int addStatus = employeeDao.addEmployee(employee);

        assertThat(employeeDao.findAll()).hasSize(4);
        assertThat(addStatus).isEqualTo(1);
        prepareTestTable();
    }

    @Test
    public void updateEmployeeTest() throws IOException {
        int updateStatus = employeeDao.updateEmployee(employee, 1L);

        assertThat(employeeDao.findById(1L).get(0).getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(employeeDao.findAll()).hasSize(3);
        assertThat(updateStatus).isEqualTo(1);

        prepareTestTable();
    }
}
