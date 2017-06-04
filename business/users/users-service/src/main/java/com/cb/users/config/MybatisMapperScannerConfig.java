package com.cb.users.config;

/**
 * Created by oo on 17-6-4.
 */
import com.cb.users.dao.base.CustomBaseMapper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@AutoConfigureAfter(MysqlConfiguration.class)
public class MybatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.cb.**.dao.mysql");
        Properties properties = new Properties();
        properties.setProperty("mappers", CustomBaseMapper.class.getName());
        mapperScannerConfigurer.setProperties(properties);
        mapperScannerConfigurer.setMarkerInterface(CustomBaseMapper.class);
        return mapperScannerConfigurer;
    }
}
