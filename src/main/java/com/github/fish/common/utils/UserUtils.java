package com.github.fish.common.utils;

import com.github.fish.blog.api.entity.SystemUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;


public class UserUtils {

    /**
     * 获取授权对象
     * @return
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static SystemUser getSystemUser(){
        Object object = getSubject().getPrincipals().getPrimaryPrincipal();
        String str = JsonUtil.obj2Json(object);
        SystemUser systemUser = JsonUtil.json2Obj(str, SystemUser.class);
        return systemUser;
    }

    /**
     * 获取session当前用户信息
     * @return
     */
    public static SystemUser getSystemUser(HttpServletRequest request){
        Object sessionData = request.getSession().getAttribute("systemUser");
        if (null == sessionData){
            return null;
        }
        String str = JsonUtil.obj2Json(sessionData);
        return JsonUtil.json2Obj(str, SystemUser.class);
    }

    /**
     * 判断当前用户是否登录
     * @return
     */
    public static boolean isLogin(){
        PrincipalCollection principalCollection = getSubject().getPrincipals();
        if(principalCollection==null){
            return false;
        }else{
            return true;
        }
    }
}
