package com.savonik.employeedb.rest;

import com.savonik.employeedb.exception.NoSuchEmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseBody
@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(SQLGrammarException.class)
    @ResponseStatus(NOT_ACCEPTABLE)
    public String handleSQLGrammarException(Exception ex) {
        log.error("SQLGrammarException occurred: " + ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(NoSuchEmployeeException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleNoSuchEmployeeException(Exception ex) {
        log.error("NoSuchEmployeeException occurred: " + ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleMethodArgumentNotValidException(Exception ex) {
        log.error("MethodArgumentNotValid occurred: " + ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(NO_CONTENT)
    public String handleEmptyResultDataAccessException(Exception ex) {
        log.error("EmptyResultDataAccessException occurred: " + ex.getMessage());
        return ex.getMessage();
    }
}
