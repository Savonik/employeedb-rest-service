package com.savonik.employeedb.service;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public int addEmployee(Employee newEmployee) {
        return employeeDao.addEmployee(newEmployee);
    }

    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    public int deleteEmployee(Long id) {
        return employeeDao.deleteEmployee(id);
    }

    public int updateEmployee(Employee employeeDetails, Long id) {
        return employeeDao.updateEmployee(employeeDetails, id);
    }
}
