package com.savonik.employeedb.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @NotBlank(message = "Name cannot be empty")
    private String firstName;
    @NotBlank(message = "Lastname cannot be empty")
    private String lastName;
    @NotNull
    @Min(1L)
    private Long departmentId;
    @NotBlank(message = "jobTitle cannot be empty")
    @Size(min = 3, max = 20)
    private String jobTitle;
    @NotNull(message = "Gender cannot be empty")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotNull(message = "Date of Birth cannot be empty")
    @Past
    private Date dateOfBirth;
}
