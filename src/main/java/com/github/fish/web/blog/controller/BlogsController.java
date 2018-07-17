package com.github.fish.web.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogsController {


    @GetMapping("blog")
    public String blogIndex() {
        return "blog";
    }
}
