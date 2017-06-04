package com.cb.users.entity.kafka;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

/**
 * 命令记录表
 *
 * @author
 * @date
 */
public class UserKafka implements Serializable {

    private Long id;
    private String username;
    private String password;
    private Integer age;
    private Date birthday;
    private Gender gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
   public String toString() {
       return ReflectionToStringBuilder.toString(this, JSON_STYLE);
   }

}
