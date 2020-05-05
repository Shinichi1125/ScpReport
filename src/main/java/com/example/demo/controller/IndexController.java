package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController implements ErrorController{
	
    private final static String PATH = "/error";
    
    @Override
    @RequestMapping(PATH)
    @ResponseBody
    public String getErrorPath() {
        return "No Mapping Found";
    }
}

// fetched from a Stack Overflow post
// https://stackoverflow.com/questions/31134333/this-application-has-no-explicit-mapping-for-error

