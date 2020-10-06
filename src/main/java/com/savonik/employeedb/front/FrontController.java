package com.savonik.employeedb.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employeedb")
public class FrontController {

    @GetMapping
    public String getAll() {
        return "index.html";
    }
}
