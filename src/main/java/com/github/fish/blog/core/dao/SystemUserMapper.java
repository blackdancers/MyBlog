package com.github.fish.blog.core.dao;

import com.github.fish.blog.api.entity.SystemUser;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface SystemUserMapper extends Mapper<SystemUser> {
    SystemUser getSystemUserByAccount(String userAccount);

    SystemUser getSystemUserByIdentifier(@Param("identifier")String identifier);

    SystemUser getSystemUserByLogin(@Param("userAccount")String userAccount,@Param("password") String password);
}