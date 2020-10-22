package com.savonik.employeedb.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savonik.employeedb.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeListener {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeListener(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @JmsListener(destination = "queue")
    public void receiveMessage(String employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        employeeService.addEmployee(objectMapper.readValue(employee, Employee.class));
        System.out.println(employee);
    }

    @JmsListener(destination = "queue")
    public void receiveAndSendMessage(String employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        employeeService.addEmployee(objectMapper.readValue(employee, Employee.class));
        System.out.println(employee);
    }
}
