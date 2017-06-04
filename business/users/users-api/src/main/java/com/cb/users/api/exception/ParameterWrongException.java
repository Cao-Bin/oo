package com.cb.users.api.exception;


import static com.cb.users.api.exception.ExceptionCode.PARAMETER_WRONG_CODE;

public class ParameterWrongException extends BusinessException {

    public ParameterWrongException(String message) {
        super(PARAMETER_WRONG_CODE, message);
    }
}
