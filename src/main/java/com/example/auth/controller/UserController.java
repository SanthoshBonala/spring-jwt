package com.example.auth.controller;

import com.example.auth.domain.CustomUser;
import com.example.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public String registerUser(@RequestBody CustomUser user){

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public Object loginUser(@RequestBody CustomUser user){

        logger.info("reached");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/api/questions")
    public String getQuestions(){
        logger.info("in get Questions");
        return "successes";
    }

}
