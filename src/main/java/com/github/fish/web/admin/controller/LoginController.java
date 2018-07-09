package com.github.fish.web.admin.controller;


import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.service.SystemUserService;
import com.github.fish.common.constant.IConstInfo;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class LoginController {


    @Resource
    private SystemUserService systemUserService;

    @GetMapping("loginUI")
    public String loginUI(){
        return "admin/login";
    }

    @PostMapping("login")
    @ResponseBody
    public SystemUser login(@RequestBody String userAccount,@RequestParam String password){

        SystemUser systemUser = systemUserService.getSystemUserByIdentifier(userAccount);
        if (null == systemUser) {
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "用户不存在.", Module.SYSTEM);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userAccount, password);
        try {
            subject.login(token);
        } catch (DisabledAccountException e) {
//            modelMap.addFlashAttribute("message","账户已禁用.");
//            return "redirect:admin/loginUI";
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "账户已禁用,请联系客服.", Module.SYSTEM);
        } catch (IncorrectCredentialsException e) {
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "用户名或密码错误.", Module.SYSTEM);
        } catch (Exception e) {
            if (e.getCause() instanceof BaseBizException) {
                throw (BaseBizException) e.getCause();
            }
            throw new BaseBizException(IConstInfo.EXCEPTION_TYPE_GENERAL, "登录失败.", Module.SYSTEM);
        }
        //登录信息存放到session中
        //TODO 使用UserUtils.getSystemUser(request)方法获取
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("systemUser",systemUser);
        return systemUser;
    }
}
