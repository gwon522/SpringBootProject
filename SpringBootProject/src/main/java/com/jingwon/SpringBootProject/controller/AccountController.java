package com.jingwon.SpringBootProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public String login(Model model){

        return "account/login";
    }

    @GetMapping("/joinus")
    public String joinus(Model model){

        return "account/joinus";
    }
}
