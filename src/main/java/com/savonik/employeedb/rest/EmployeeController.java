package com.savonik.employeedb.rest;

import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @PostMapping
    public ResponseEntity<Object> addNewEmployee(@RequestBody Employee newEmployee) {
        int addResult = employeeService.addEmployee(newEmployee);
        return addResult == 0 ?
                ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable(value = "id") Long id) {
        Employee employee;
        try {
            employee = employeeService.findById(id);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value = "id") Long id) {
        int deleteResult = employeeService.deleteEmployee(id);
        return deleteResult == 0 ?
                ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employeeDetails, @PathVariable Long id) {
        int updateResult = employeeService.updateEmployee(employeeDetails, id);
        return updateResult == 0 ?
                ResponseEntity.notFound().build() : ResponseEntity.ok().build();
    }
}
