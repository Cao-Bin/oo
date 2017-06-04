package com.cb.users.util;

import org.apache.commons.lang3.StringUtils;

public class RedisKeyUtil {
    private final static String separator = ":";


    private static String joinString(String ... target){
        return StringUtils.join(target, separator);
    }


}
