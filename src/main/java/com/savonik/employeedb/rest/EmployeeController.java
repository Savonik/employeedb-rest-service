package com.savonik.employeedb.rest;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import jdk.management.resource.ResourceRequestDeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeDao dao;

    @Autowired
    public EmployeeController(EmployeeDao dao) {
        this.dao = dao;
    }

    @GetMapping("/employees")
    public List<Employee> getAll() {
        return dao.findAll();
    }

    @PostMapping("/employees")
    @ResponseBody
    public int addNewEmployee(@RequestBody Employee newEmployee) {
        return dao.addEmployee(newEmployee);
    }

    @GetMapping("/employees/{id}")
    public Employee getById(@PathVariable(value = "id") Long id) {
        if (dao.findById(id) == null){
            throw new ResourceRequestDeniedException("Employee not exist with id :" + id);
        }
        return dao.findById(id);
    }

    @DeleteMapping("/employees/{id}")
    @ResponseBody
    public int deleteEmployee(@PathVariable(value = "id") Long id) {
        if (dao.deleteEmployee(id) == 0){
            throw new ResourceRequestDeniedException("Cannot delete row with id :" + id);
        }
        return dao.deleteEmployee(id);
    }

    @PutMapping("/employees/{id}")
    int updateEmployee(@RequestBody Employee employeeDetails, @PathVariable Long id) {
        if (dao.updateEmployee(employeeDetails, id) == 0){
            throw new ResourceRequestDeniedException("Cannot delete row with id :" + id);
        }
        return dao.updateEmployee(employeeDetails, id);
    }

}
