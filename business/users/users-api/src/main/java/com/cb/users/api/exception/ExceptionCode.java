package com.cb.users.api.exception;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class ExceptionCode {
    public final static String PARAMETER_WRONG_CODE = "10000";
    public final static String PARAMETER_WRONG_MSG = "参数错误";

    public final static String COMMAND_EXECUTE_FAILURE_CODE = "10001";
    public final static String COMMAND_EXECUTE_FAILURE_MSG = "命令发送失败.";

    public final static String UNAUTHORIZED_VEHICLE_CODE = "10002";
    public final static String UNAUTHORIZED_VEHICLE_MSG = "未授权车辆";

    public final static String  COMMAND_RECORD_NOT_FOUND_CODE = "10003";
    public final static String COMMAND_RECORD_NOT_FOUND_MSG = "命令记录未找到.";

    public final static String UNKNOW_VEHICLE_TOKEN_CODE = "10004";
    public final static String UNKNOW_VEHICLE_TOKEN_MSG = "非法的车辆token。";



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
