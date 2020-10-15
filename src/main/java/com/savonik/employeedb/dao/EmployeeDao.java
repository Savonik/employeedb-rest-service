package com.savonik.employeedb.dao;

import com.savonik.employeedb.dto.Employee;
import com.savonik.employeedb.dto.Gender;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.firstName=:firstName, " +
            "e.lastName=:lastName," +
            "e.departmentId=:departmentId," +
            "e.jobTitle=:jobTitle," +
            "e.gender=:gender," +
            "e.dateOfBirth=:dateOfBirth WHERE e.employeeId =:employeeId")
    int updateEmployee(@Param("employeeId") Long employeeId,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName,
                       @Param("departmentId") Long departmentId,
                       @Param("jobTitle") String jobTitle,
                       @Param("gender") Gender gender,
                       @Param("dateOfBirth") Date dateOfBirth);

}


