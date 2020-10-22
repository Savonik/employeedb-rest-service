package com.savonik.employeedb.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savonik.employeedb.dto.Employee;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequestMapping("/employees/queue")
@ApiResponses(@ApiResponse(code = 500, message = "SERVER ERROR"))
@RestController
public class ActiveMQEmployeeController {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMQEmployeeController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Async
    @PostMapping
    @ApiOperation("add a new employee with JMS")
    @ResponseStatus(CREATED)
    public void addNewEmployee(@Valid @RequestBody Employee employee) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("Trying to create " + employee.toString());
        jmsTemplate.convertAndSend("queue", objectMapper.writeValueAsString(employee));
    }
}
