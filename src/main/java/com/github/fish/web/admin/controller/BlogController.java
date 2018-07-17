package com.github.fish.web.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class BlogController {


    @GetMapping("blog")
    public String blogManage() {
        return "admin/blogs";
    }

    @GetMapping("addCategory")
    public String addCategory() {
        return "admin/addCategory";
    }
}
