package com.savonik.employeedb.dao;

import com.savonik.employeedb.dto.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeDao extends CrudRepository<Employee, Long> {

}


