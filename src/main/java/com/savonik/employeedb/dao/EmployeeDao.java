package com.savonik.employeedb.dao;

import com.savonik.employeedb.dto.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

}


