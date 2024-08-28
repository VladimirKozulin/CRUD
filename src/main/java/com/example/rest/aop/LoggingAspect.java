package com.example.rest.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("@annotation(Loggable)")
    public void logBegore(JoinPoint point) {
        log.info("Before execution of: {}", point.getSignature().getName());
    }

    @After("@annotation(Loggable)")
    public void logAfter(JoinPoint point) {
        log.info("After execution of: {}", point.getSignature().getName());
    }

    @AfterReturning(pointcut = "@annotation(Loggable)",returning = "result")
    public void logAfterReturning(JoinPoint point, Object result) {
        log.info("After returning from: {}", point.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "@annotation(Loggable)",throwing = "exception")
    public void logAfterThrowing(JoinPoint point, Exception exception) {
        log.info("After throwing from: {}", point.getSignature().getName(),exception);
    }

    @Around ("@annotation(Loggable)")
    public Object logAround(ProceedingJoinPoint point) throws Throwable{
        log.info("Around method: {} is called", point.getSignature().getName());

        Object result = point.proceed();

        log.info("Around method: {} is finished", point.getSignature().getName());

        return result;
    }
}
