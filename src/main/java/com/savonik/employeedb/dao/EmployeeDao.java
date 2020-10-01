package com.savonik.employeedb.dao;

import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        final String query = "SELECT " +
                "employee_id," +
                "first_name," +
                "last_name," +
                "department_id," +
                "job_title," +
                "gender," +
                "date_of_birth " +
                "FROM employee;";

        return jdbcTemplate.query(query, (rs, i) -> new Employee(
                rs.getLong("employee_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getLong("department_id"),
                rs.getString("job_title"),
                Gender.valueOf(rs.getString("gender").trim()),
                new Date(rs.getDate("date_of_birth").getTime())
        ));
    }

    public Employee findById(Long id) {
        final String query = "SELECT " +
                "employee_id," +
                "first_name," +
                "last_name," +
                "department_id," +
                "job_title," +
                "gender," +
                "date_of_birth " +
                "FROM employee " +
                "WHERE employee_id = ?;";

        return jdbcTemplate.query(query, new Object[]{id}, rs -> {
            if (!rs.next()) {
                throw new NullPointerException("Employee with this id wasn't found");
            }
            return new Employee(
                    rs.getLong("employee_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getLong("department_id"),
                    rs.getString("job_title"),
                    Gender.valueOf(rs.getString("gender").trim()),
                    new Date(rs.getDate("date_of_birth").getTime())
            );
        });
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
                newEmployee.getBirthday());
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
                employeeDetails.getGender().toString(),
                employeeDetails.getBirthday(),
                id);
    }
}
