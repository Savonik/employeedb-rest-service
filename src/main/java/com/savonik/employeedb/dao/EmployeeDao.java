package com.savonik.employeedb.dao;

import com.savonik.employeedb.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.jdbc.core.BeanPropertyRowMapper.newInstance;

@Repository
public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        final String query = "SELECT * FROM employee ORDER BY employee_id";
        return jdbcTemplate.query(query, newInstance(Employee.class));
    }

    public List<Employee> findById(Long id) {
        final String query = "SELECT * FROM employee WHERE employee_id = ?";
        return jdbcTemplate.query(query, newInstance(Employee.class), id);
    }

    public int addEmployee(Employee newEmployee) {
        final String query = "INSERT INTO employee(" +
                "first_name," +
                "last_name," +
                "department_id," +
                "job_title," +
                "gender," +
                "date_of_birth) " +
                "VALUES(?, ?, ?, ?, ?, ?);";

        return jdbcTemplate.update(
                query,
                newEmployee.getFirstName(),
                newEmployee.getLastName(),
                newEmployee.getDepartmentId(),
                newEmployee.getJobTitle(),
                String.valueOf(newEmployee.getGender()),
                newEmployee.getDateOfBirth());
    }

    public int deleteEmployee(Long id) {
        final String query = "DELETE FROM employee WHERE employee_id = ?";
        return jdbcTemplate.update(query, id);
    }

    public int updateEmployee(Employee employeeDetails, long id) {
        final String query = "UPDATE employee " +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "department_id = ?, " +
                "job_title = ?, " +
                "gender = ?," +
                "date_of_birth = ? " +
                "WHERE employee_id = ?;";

        return jdbcTemplate.update(
                query,
                employeeDetails.getFirstName(),
                employeeDetails.getLastName(),
                employeeDetails.getDepartmentId(),
                employeeDetails.getJobTitle(),
                String.valueOf(employeeDetails.getGender()),
                employeeDetails.getDateOfBirth(),
                id);
    }

    public void query(String query) {
        jdbcTemplate.update(query);
    }
}
