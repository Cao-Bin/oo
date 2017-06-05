package com.cb.users.util;

import com.cb.users.UsersApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by CaoBin on 17-5-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UsersApplication.class)
@WebAppConfiguration
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void cacheList(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        redisUtil.cacheList("test",JsonUtil.toJson(integers));
    }



}
