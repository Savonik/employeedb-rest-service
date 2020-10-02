package com.savonik.employeedb.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/employee")
public class FrontController {

    @GetMapping
    public String getAll() {
        return "employee.html";
    }

}
