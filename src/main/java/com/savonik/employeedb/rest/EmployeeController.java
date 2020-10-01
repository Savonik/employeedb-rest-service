package com.savonik.employeedb.rest;

import com.savonik.employeedb.dao.EmployeeDao;
import com.savonik.employeedb.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private static final String SUCCESS_STATUS = "success";
    private static final String ERROR_STATUS = "error";

    private final EmployeeDao dao;

    @Autowired
    public EmployeeController(EmployeeDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public RestResponse getAll() {
        List<Employee> employees = dao.findAll();
        return employees == null ? new RestResponse(ERROR_STATUS) : new RestResponse(SUCCESS_STATUS, employees);
    }

    @PostMapping
    public RestResponse addNewEmployee(@RequestBody Employee newEmployee) {
        int addResult = dao.addEmployee(newEmployee);
        return addResult == 0 ? new RestResponse(ERROR_STATUS) : new RestResponse(SUCCESS_STATUS);
    }

    @GetMapping("/{id}")
    public RestResponse getById(@PathVariable(value = "id") Long id) {
        Employee employee = dao.findById(id);
        return employee == null ? new RestResponse(ERROR_STATUS) : new RestResponse(SUCCESS_STATUS, employee);
    }

    @DeleteMapping("/{id}")
    RestResponse deleteEmployee(@PathVariable(value = "id") Long id) {
        int deleteResult = dao.deleteEmployee(id);
        return deleteResult == 0 ? new RestResponse(ERROR_STATUS) : new RestResponse(SUCCESS_STATUS);
    }

    @PutMapping("/{id}")
    RestResponse updateEmployee(@RequestBody Employee employeeDetails, @PathVariable Long id) {
        int updateResult = dao.updateEmployee(employeeDetails, id);
        return updateResult == 1 ?
                new RestResponse(SUCCESS_STATUS) : new RestResponse(ERROR_STATUS);
    }
}
