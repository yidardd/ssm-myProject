package com.txn.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MyPermissionAspect {

    @Pointcut("@annotation(com.txn.annotation.MyPermission)")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MyPermission annotation = joinPoint.getClass().getAnnotation(MyPermission.class);
        String value = annotation.value();
        System.err.println("请求的类上面的"+value);
    }

}