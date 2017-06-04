//package com.cb.users.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
///**
// * Created with IntelliJ IDEA.
// * User: hengsun
// * Date: 5/14/17
// * Time: 8:29 PM
// * Description:
// */
//@Configuration
//public class RedisConfiguration {
//    @Autowired
//    private Environment env;
//
//    @Bean(name = "redisTemplate")
//    public RedisTemplate redisTemplate(){
//        Redis redis = new Redis();
//        redis.setIp(env.getProperty("redis.ip"));
//        redis.setPort(Integer.parseInt(env.getProperty("redis.port")));
//        redis.setDb(Integer.parseInt(env.getProperty("redis.db")));
//        redis.setShareDb(Integer.parseInt(env.getProperty("redis.shareDb")));
//        redis.setPassWord(env.getProperty("redis.passWord"));
//        redis.setTimeOut(Integer.valueOf(env.getProperty("redis.timeOut")));
//        RedisTemplate redisTemplate = new RedisTemplate(redis);
//        return  redisTemplate;
//    }
//}
