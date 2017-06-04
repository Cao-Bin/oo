package com.cb.users.dao.mongo;

import com.cb.users.UsersApplication;
import com.cb.users.entity.enums.Gender;
import com.cb.users.entity.mongo.UserEntity;
import com.cb.users.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by CaoBin on 17-5-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UsersApplication.class)
@WebAppConfiguration
public class UserDAOTest {

    @Autowired
    private UserDOC userDOC;

    @Test
    public void save(){
        UserEntity userEntity=new UserEntity();
        userEntity.setAge(22l);
        userEntity.setBirthday(new Date());
        userEntity.setGender(Gender.FEMALE);
        userEntity.setPassword("111111");
        userEntity.setUsername("wangwu");
        userEntity= userDOC.save(userEntity);

        Assert.assertNotNull(userEntity);

        System.out.println(JsonUtil.toJson(userEntity));

    }






}
