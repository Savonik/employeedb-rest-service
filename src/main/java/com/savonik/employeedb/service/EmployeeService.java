package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
            throw new NoSuchElementException();
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
        employeeDetails.setEmployeeId(id);
        return employeeDao.save(employeeDetails);
    }
}
