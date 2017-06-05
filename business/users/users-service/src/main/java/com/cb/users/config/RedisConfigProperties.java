package com.cb.users.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.redis", ignoreInvalidFields = true)
public class RedisConfigProperties {
    private Long database;
    private String  host;
    private String  password;
    private Integer port;
    private Integer timeout;
    private RedisPoolConfigProperties pool;

    public Long getDatabase() {
        return database;
    }

    public void setDatabase(Long database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public RedisPoolConfigProperties getPool() {
        return pool;
    }

    public void setPool(RedisPoolConfigProperties pool) {
        this.pool = pool;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
