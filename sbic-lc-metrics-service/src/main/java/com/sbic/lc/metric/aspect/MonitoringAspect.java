package com.sbic.lc.metric.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MonitoringAspect {

    @Around("@annotation(Monitor)")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        //This is where ACTUAL METHOD will get invoke
        Object result = joinPoint.proceed();

        // AFTER METHOD EXECUTION
        System.out.println(result);
        return result;
    }
}
