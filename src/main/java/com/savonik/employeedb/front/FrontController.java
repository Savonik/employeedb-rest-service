package com.savonik.employeedb.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees-list")
public class FrontController {

    @GetMapping
    public String getAll() {
        return "index.html";
    }

    @GetMapping(value = "/{id}")
    //@ResponseBody
    public String getById(@PathVariable(value = "id") Long id) {
        return "employee";
    }
}
