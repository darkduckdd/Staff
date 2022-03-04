package com.darkduck.Staff.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/errorPage")
    public String errorPage(){
        return "views/error";
    }
}
