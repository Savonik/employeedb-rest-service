package com.savonik.employeedb.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Employee {

    private Long employeeId;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Long departmentId;
    @NonNull
    private String jobTitle;
    @NonNull
    private Gender gender;
    @NonNull
    private Date dateOfBirth;
}
