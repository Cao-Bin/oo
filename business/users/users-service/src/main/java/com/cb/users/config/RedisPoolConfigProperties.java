package com.cb.users.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class RedisPoolConfigProperties {
    private Long maxActive;
    private Long maxIdle;
    private Long maxWait;
    private Long minIdle;

    public Long getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Long maxActive) {
        this.maxActive = maxActive;
    }

    public Long getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Long maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Long maxWait) {
        this.maxWait = maxWait;
    }

    public Long getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Long minIdle) {
        this.minIdle = minIdle;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
