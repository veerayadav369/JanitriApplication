package com.janitri.JanitriApplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndexPage(Model model) {
        System.out.println("Index page requested!");
        model.addAttribute("message", "Welcome to the Homepage!");
        return "index";
    }
}
