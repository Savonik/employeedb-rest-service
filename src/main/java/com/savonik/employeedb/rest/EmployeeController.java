package com.savonik.employeedb.rest;

import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.service.EmployeeService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j
@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> getAll() {
        log.info("Trying to get employee list");
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable(value = "id") Long id) {
        log.info("Trying to find employee with id = " + id);
        return employeeService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        log.info("Trying to create " + employee.toString());
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long id) {
        log.info("Trying to delete employee with id = " + id);
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        log.info(String.format("Trying to update employee with id = %s, employee details: %s", id, employeeDetails.toString()));
        return employeeService.updateEmployee(id, employeeDetails);
    }
}
