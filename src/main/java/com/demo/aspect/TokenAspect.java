package com.demo.aspect;


import com.demo.annotation.NeedToken;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
//public class TokenAspect {
//    @Before("@annotation(needToken)")
//    public Object before(JoinPoint joinPoint, NeedToken needToken) throws Throwable {
//
//    }
//}
