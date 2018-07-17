package com.github.fish.web.admin.controller;


import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.service.SystemUserService;
import com.github.fish.common.constant.IConstInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
        return "redirect:/admin";
    }

    @PostMapping("login")
    public String login(@RequestParam String userAccount,
                            @RequestParam String password,
                            RedirectAttributes attributes){

        SystemUser user = systemUserService.getSystemUserByIdentifier(userAccount);
        if (null == user) {
            attributes.addFlashAttribute("message","用户不存在");
            return "redirect:/admin";
        }
        //登录
        SystemUser systemUser = systemUserService.getSystemUserByLogin(userAccount, password);
        if (null == systemUser) {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }

        //登录信息存放到session中
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute(IConstInfo.CURRENT_USER,systemUser);
        return "admin/index";
    }
}
