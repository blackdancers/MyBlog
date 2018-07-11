package com.github.fish.web.admin.controller;


import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.service.SystemUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class LoginController {


    @Resource
    private SystemUserService systemUserService;

    @GetMapping("")
    public String loginUI(){
        return "admin/login";
    }


    @GetMapping("logout")
    public String logout(){
        return "admin/login";
    }

    @PostMapping("login")
    public String login(@RequestParam String userAccount,
                        @RequestParam String password,
                        RedirectAttributes attributes,
                        HttpSession session){

        SystemUser user = systemUserService.getSystemUserByIdentifier(userAccount);
        if (null == user) {
            attributes.addFlashAttribute("message","用户不存在.");
            return "redirect:/admin";
        }
        //登录
        SystemUser systemUser = systemUserService.getSystemUserByLogin(userAccount, password);
        if (null == systemUser) {
            attributes.addFlashAttribute("message","用户名或密码错误.");
            return "redirect:/admin";
        }

        //登录信息存放到session中
        session.setAttribute("systemUser",systemUser);
        return "admin/index";
    }
}
