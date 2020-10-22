package com.savonik.employeedb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeServiceException extends RuntimeException {

    public EmployeeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeServiceException(Throwable cause) {
        super(cause);
    }

    public EmployeeServiceException(String message) {
        super(message);
    }
}
