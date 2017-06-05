package com.cb.users.dao.mysql;

import com.cb.users.UsersApplication;
import com.cb.users.api.vo.UserVO;
import com.cb.users.entity.enums.Gender;
import com.cb.users.entity.mysql.UserEntity;
import com.cb.users.service.user.UserService;
import com.cb.users.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import tk.mybatis.mapper.entity.Condition;

import java.util.Date;
import java.util.List;

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
    private UserService userService;

    @Test
    public void selectById(){
        UserEntity userEntity = userDao.selectByPrimaryKey(1L);
        Assert.assertNotNull(userEntity);
        System.out.println(JsonUtil.toJson(userEntity));
    }

    @Test
    public void findUsers(){
        List<UserVO> all = userService.findUsers(null,1,1);
        Assert.assertNotNull(all);
        System.out.println(JsonUtil.toJson(all));
    }

    @Test
    public void save(){
        UserEntity userEntity=new UserEntity();
        userEntity.setAge(22l);
        userEntity.setBirthday(new Date());
        userEntity.setGender(Gender.FEMALE);
        userEntity.setPassword("111111");
        userEntity.setUsername("wangwu");
        userEntity.setVersion(1l);
        int i = userDao.insert(userEntity);

        Assert.assertNotEquals(i,0);

        System.out.println(JsonUtil.toJson(i));
    }


    @Test
    public void update(){
        UserEntity userEntity=new UserEntity();
        userEntity.setAge(24l);

        Condition condition=new Condition(UserEntity.class);
        condition.createCriteria().andEqualTo("id",1).andEqualTo("version",2);
        int i = userDao.updateByConditionSelective(userEntity, condition);

        Assert.assertNotEquals(i,0);

        System.out.println( JsonUtil.toJson(i));
    }





}
