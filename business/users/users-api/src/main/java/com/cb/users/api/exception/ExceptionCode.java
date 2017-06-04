package com.cb.users.api.exception;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ExceptionCode {

    public final static String INTERNAL_SERVER_ERROR_CODE = "10000";
    public final static String INTERNAL_SERVER_ERROR_MSG = "系统内部错误.";

    public final static String PARAMETER_WRONG_CODE = "10001";
    public final static String PARAMETER_WRONG_MSG = "参数错误.";






    //validate unique code when boot.
    static {
        try {
            Class<ExceptionCode> exceptionCdoeClass = ExceptionCode.class;
            Field[] fields = exceptionCdoeClass.getFields();
            Set<String> codeList = new HashSet<>();
            if (fields != null) {
                for (Field f : fields) {
                     if (f.getName().trim().toUpperCase().endsWith("CODE")) {
                         String codeValue = ((String) f.get(exceptionCdoeClass)).trim();
                            if(codeList.contains(codeValue))
                                throw new RuntimeException("error code defined repeat");
                            codeList.add(codeValue);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
