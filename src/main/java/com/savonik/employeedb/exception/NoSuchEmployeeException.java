package com.savonik.employeedb.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchEmployeeException extends RuntimeException {

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}
