package com.savonik.employeedb.rest;

import com.savonik.employeedb.exception.EmployeeServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(EmployeeServiceException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleEmployeeServiceException(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleMethodArgumentNotValidException(Exception ex) {
        log.error("MethodArgumentNotValid occurred: " + ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public String handleException(Exception ex) {
        log.error("Exception occurred: " + ex.getMessage(), ex);
        return "Internal Server Error.\nSorry, something went wrong";
    }
}
