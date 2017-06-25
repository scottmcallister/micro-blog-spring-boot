package com.example.microblogspringboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by scottmcallister on 2017-06-22.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("message", "Hello World!");
        return "home";
    }
}
