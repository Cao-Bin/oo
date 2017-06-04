package com.cb.users.aop;

import com.cb.users.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by oo on 17-6-4.
 */
@Aspect
@Component
public class LogAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.cb.users.controller..*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        LOGGER.info("URL : {}",request.getRequestURL().toString());
        LOGGER.info("HTTP_METHOD : {}",request.getMethod());
        LOGGER.info("IP : {}",request.getRemoteAddr());
        LOGGER.info("CLASS_METHOD : {}.{}",joinPoint.getSignature().getDeclaringTypeName() ,joinPoint.getSignature().getName());
        LOGGER.info("ARGS : {}", JsonUtil.toJson(joinPoint.getArgs()));
    }
    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        LOGGER.info("RESPONSE : {}", JsonUtil.toJson(ret));
        LOGGER.info("SPEND TIME : {}",(System.currentTimeMillis() - startTime.get()));

    }
}
