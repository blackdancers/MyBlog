package com.github.fish.blog.service.impl;

import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.service.SystemUserService;
import com.github.fish.blog.core.biz.SystemUserBiz;
import com.github.fish.common.exceptions.BaseBizException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SystemUserBiz systemUserBiz;

    @Override
    public SystemUser getSystemUserByAccount(String userAccount) {
        return systemUserBiz.getSystemUserByAccount(userAccount);
    }

    @Override
    public SystemUser getSystemUserByLogin(String userAccount, String password) throws BaseBizException {
        return systemUserBiz.getSystemUserByLogin(userAccount, password);
    }

    public SystemUser getSystemUserById(Long id) {
        return systemUserBiz.getSystemUserById(id);
    }

    public Long addSystemUser(SystemUser systemUser) throws BaseBizException {
        return systemUserBiz.addSystemUser(systemUser);
    }

    @Override
    public SystemUser getSystemUserByIdentifier(String userAccount) throws BaseBizException {
        return systemUserBiz.getSystemUserByIdentifier(userAccount);
    }
}
