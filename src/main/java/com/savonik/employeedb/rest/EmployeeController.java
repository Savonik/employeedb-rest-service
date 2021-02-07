package com.savonik.employeedb.rest;

import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequestMapping("/employees")
@RestController
@Api("Employee-controller")
@ApiResponses(@ApiResponse(code = 500, message = "SERVER ERROR"))
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApiOperation("get a list of employees")
    public Iterable<Employee> getAll() {
        log.info("Trying to get employee list");
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("get an employee by id")
    @ApiResponse(code = 404, message = "NOT FOUND")
    public Employee getById(@PathVariable(value = "id") Long id) {
        log.info("Trying to find employee with id = " + id);
        return employeeService.getById(id);
    }

    @PostMapping
    @ApiOperation("add a new employee to employee list")
    @ApiResponse(code = 400, message = "BAD REQUEST")
    @ResponseStatus(CREATED)
    public Employee addEmployee(@Valid @RequestBody Employee employee) {
        log.info("Trying to create " + employee.toString());
        return employeeService.addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete an employee by id")
    @ApiResponse(code = 404, message = "NOT FOUND")
    public void deleteEmployee(@PathVariable(value = "id") Long id) {
        log.info("Trying to delete employee with id = " + id);
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("change employee data by id")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "NOT FOUND"),
            @ApiResponse(code = 400, message = "BAD REQUEST")})
    public Employee updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employeeDetails) {
        log.info(String.format("Trying to update employee with id = %s, employee details: %s", id, employeeDetails.toString()));
        return employeeService.updateEmployee(id, employeeDetails);
    }
}
