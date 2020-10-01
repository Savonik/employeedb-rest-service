package com.savonik.employeedb.rest;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeDao dao;

    @Autowired
    public EmployeeController(EmployeeDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Employee> getAll() {
        return dao.findAll();
    }

    @PostMapping
    @ResponseBody
    public int addNewEmployee(@RequestBody Employee newEmployee) {
        return dao.addEmployee(newEmployee);
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(value = "id") Long id) {
        Employee employee = dao.findById(id);
        if (employee == null) {
            throw new ResourceRequestDeniedException("Employee does not exist with id :" + id);
        }
        return employee;
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public int deleteEmployee(@PathVariable(value = "id") Long id) {
        int deleteResult = dao.deleteEmployee(id);
        if (deleteResult == 0) {
            throw new ResourceRequestDeniedException("Cannot delete row with id :" + id);
        }
        return deleteResult;
    }

    @PutMapping("/{id}")
    int updateEmployee(@RequestBody Employee employeeDetails, @PathVariable Long id) {
        int updateResult = dao.updateEmployee(employeeDetails, id);
        if (updateResult == 0) {
            throw new ResourceRequestDeniedException("Cannot update row with id :" + id);
        }
        return updateResult;
    }

}
