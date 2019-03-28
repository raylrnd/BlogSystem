package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/about")
    public String getAbout(){
        return "aboutme";
    }
    @RequestMapping("/a")
    public String get(){
        return "one_article_view";
    }

}