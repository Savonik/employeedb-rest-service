package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    public List<Employee> getById(Long id) {
        return employeeDao.findById(id);
    }

    public void addEmployee(Employee newEmployee) {
        employeeDao.addEmployee(newEmployee);
    }

    public int deleteEmployee(Long id) {
        int deleteStatus = employeeDao.deleteEmployee(id);
        if (deleteStatus == 0) {
            throw new NoSuchElementException();
        }
        return deleteStatus;
    }

    public int updateEmployee(Employee employeeDetails, Long id) {
        int updateStatus = employeeDao.updateEmployee(employeeDetails, id);
        if (updateStatus == 0) {
            throw new NoSuchElementException();
        }
        return updateStatus;
    }
}
