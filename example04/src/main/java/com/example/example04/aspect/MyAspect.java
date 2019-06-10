package com.example.example04.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Aspect
public class MyAspect {

    @Around("execution(* com.example..*.buy*(..))")
    public Object calulateExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object result=joinPoint.proceed();
        long end = System.nanoTime();
        System.out.println(joinPoint.getSignature().getName()+"->"+(end-start));
        return result;
    }
    @Around("@within(myInterceptor)||@annotation(myInterceptor)")
    public Object interceporTarget(ProceedingJoinPoint joinPoint,MyInterceptor myInterceptor) throws Throwable {
        Optional.ofNullable(myInterceptor)
                .or(()->{
                    MyInterceptor m=
                            joinPoint.getTarget().getClass().getAnnotation(MyInterceptor.class);
                    return Optional.of(m);
                })
                .ifPresent(m->{
                    for(MyInterceptor.AuthorityType t:m.value()){
                        System.out.println("当前执行方法权限："+t);
                    }
                });
        return joinPoint.proceed();
    }
}
