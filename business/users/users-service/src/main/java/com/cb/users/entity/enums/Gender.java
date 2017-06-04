package com.cb.users.entity.enums;

/**
 * Created by oo on 17-6-4.
 */
public enum Gender {
    MALE("男"),
    FEMALE("女"),

    ;

    private String desc;

    private Gender(String desc) {
        this.desc = desc;
    }
}
