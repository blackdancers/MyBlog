package com.github.fish.blog.core.biz;


import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.core.dao.SystemUserMapper;
import com.github.fish.common.enums.Module;
import com.github.fish.common.exceptions.BaseBizException;
import com.github.fish.common.utils.CoderUtil;
import com.github.fish.common.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *   @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
 *
 *
 */
@Service("systemUserBiz")
//@Transactional(propagation = Propagation.REQUIRED)
@Transactional(readOnly = true)
public class SystemUserBiz {

    private static final Logger logger = LoggerFactory.getLogger(SystemUserBiz.class);

    @Resource
    private SystemUserMapper systemUserMapper;

    public SystemUser getSystemUserByAccount(String userAccount) {
        return systemUserMapper.getSystemUserByAccount(userAccount);
    }

    public SystemUser getSystemUserByLogin(String userAccount, String password) throws BaseBizException {
        //密码是明文需要加密与之匹配
        password = CoderUtil.encrypt(password);
        return systemUserMapper.getSystemUserByLogin(userAccount,password);
    }


    //方法上注解属性会覆盖类注解上的相同属性
    //@Transactional(readOnly = true)
    public SystemUser getSystemUserById(Long id){
        return systemUserMapper.selectByPrimaryKey(id);
    }
    //方法上注解属性会覆盖类注解上的相同属性
    @Transactional(propagation = Propagation.REQUIRED)
    public Long addSystemUser(SystemUser systemUser) throws BaseBizException{
        //密码加密
        if (StringUtils.isNotBlank(systemUser.getPassword())){
            systemUser.setPassword(CoderUtil.encrypt(systemUser.getPassword()));
        }
        //手机号加密
        systemUser.encrypt();
        int res = systemUserMapper.insert(systemUser);
        if (res<=0){
            throw new BaseBizException(-1,"添加失败", Module.BASE);
        }
        return systemUser.getId();
    }

    public SystemUser getSystemUserByIdentifier(String userAccount) throws BaseBizException{
        SystemUser systemUser = systemUserMapper.getSystemUserByIdentifier(userAccount);
        logger.debug("查询到 {}, 用户信息 = {}", userAccount, JsonUtil.obj2Json(systemUser));
        if (null != systemUser) {
            systemUser.decrypt();
        }
        return systemUser;
    }

}
