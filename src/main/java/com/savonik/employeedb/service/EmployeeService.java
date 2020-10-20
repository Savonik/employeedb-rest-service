package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.exception.NoSuchEmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Iterable<Employee> getAll() {
        return employeeDao.findAll();
    }

    public Employee getById(Long id) {
        Optional<Employee> employee = employeeDao.findById(id);
        if (!employee.isPresent()) {
            throw new NoSuchEmployeeException(String.format("Employee with id = %s doesn't exist", id));
        }
        return employee.get();
    }

    public Employee addEmployee(Employee newEmployee) {
        return employeeDao.save(newEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeDao.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        log.info(String.format("Check if employee with id = %s presents", id));
        if (!employeeDao.findById(id).isPresent()) {
            throw new NoSuchEmployeeException(String.format("Employee with id = %s doesn't exist", id));
        }
        employeeDetails.setEmployeeId(id);
        return employeeDao.save(employeeDetails);
    }
}
