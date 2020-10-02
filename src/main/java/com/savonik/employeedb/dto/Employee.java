package com.savonik.employeedb.dto;

import java.util.Date;

public class Employee {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final Long departmentId;
    private final String jobTitle;
    private final Gender gender;
    private final Date birthday;

    public Employee(Long id, String firstName, String lastName, Long departmentId, String jobTitle, Gender gender, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Long getId() {
        return id;
    }
}
