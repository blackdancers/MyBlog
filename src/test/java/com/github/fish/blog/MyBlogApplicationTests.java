package com.github.fish.blog;

import com.github.fish.blog.api.entity.SystemUser;
import com.github.fish.blog.api.service.SystemUserService;
import com.github.fish.common.utils.CoderUtil;
import com.github.fish.common.utils.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBlogApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private SystemUserService systemUserService;

	@Test
	public void getSystemUser(){
		Long id = 1L;
        SystemUser systemUser = systemUserService.getSystemUserById(id);
        System.out.println(JsonUtil.obj2Json(systemUser));
    }

    @Test
    public void testAddSystemUser() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("野猪佩奇");
        systemUser.setUserAccount("18611899696");
        systemUser.setCreateDate(new Date());
        systemUser.setSex("0");
        systemUser.setMobilePhone("18611899696");
        systemUser.setUserEmail("245675499@qq.com");
        systemUser.setUserStatus("0");
        String password = CoderUtil.encrypt("fb1234");
        systemUser.setPassword(password);
        systemUser.encrypt();
        Long id = systemUserService.addSystemUser(systemUser);
        logger.debug("测试添加用户，ID={}",id);
    }


	@Test
	public void contextLoads() {
	}

}
