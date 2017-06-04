package com.cb.users.aop;

import com.cb.users.api.exception.BusinessException;
import com.cb.users.api.exception.ExceptionCode;
import com.cb.users.api.vo.ErrorResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice({"com.cb.users.controller"})
public class BusinessExceptionAdvice {

    private static Logger logger = LoggerFactory.getLogger(BusinessExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseVO> handleBizExp(Exception e){
        ErrorResponseVO errorResponseVO = new ErrorResponseVO();
        if(e instanceof BusinessException){
            BusinessException are = (BusinessException) e;

            logger.error("Business Exception:"+are,are);
            errorResponseVO.setCode(are.getCode());
            errorResponseVO.setMsg(are.getMessage());

            return ResponseEntity.badRequest().body(errorResponseVO);

        } else if (e instanceof MethodArgumentNotValidException) {
            logger.error(e.getMessage(),e);

            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) e;
            String column = manve.getBindingResult().getFieldError().getField();
            String message = manve.getBindingResult().getFieldError().getDefaultMessage();
            String errorMessage =new StringBuffer(ExceptionCode.PARAMETER_WRONG_MSG)
                    .append(":")
                    .append(column)
                    .append(" ")
                    .append(message).toString();
            errorResponseVO.setCode(ExceptionCode.PARAMETER_WRONG_CODE);
            errorResponseVO.setMsg(errorMessage);
            return ResponseEntity.badRequest().body(errorResponseVO);
        }else{
            errorResponseVO.setCode(ExceptionCode.INTERNAL_SERVER_ERROR_CODE);
            errorResponseVO.setMsg(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseVO);
        }
    }


}
