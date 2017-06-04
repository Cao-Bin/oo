package com.cb.users.dao.mysql;

import com.cb.users.UsersApplication;
import com.cb.users.entity.mysql.UserEntity;
import com.cb.users.service.json.JsonService;
import com.cb.users.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by CaoBin on 17-5-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UsersApplication.class)
@WebAppConfiguration
public class UserDAOTest {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private JsonService jsonService;

    @Test
    public void selectById(){
        UserEntity userEntity = userDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(userEntity);
        System.out.println(JsonUtil.toJson(userEntity));
    }





}
