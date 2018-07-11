package com.github.fish.blog.api.service;


import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.common.exceptions.BaseBizException;

public interface SystemUserService {

    /**
     *
     * @param userAccount 用户账号
     * @return
     */
    public SystemUser getSystemUserByAccount(String userAccount);

    public SystemUser getSystemUserByLogin(String userAccount,String password) throws BaseBizException ;

    public SystemUser getSystemUserById(Long id);

    public Long addSystemUser(SystemUser systemUser) throws BaseBizException ;

    public SystemUser getSystemUserByIdentifier(String userAccount) throws BaseBizException;
}
