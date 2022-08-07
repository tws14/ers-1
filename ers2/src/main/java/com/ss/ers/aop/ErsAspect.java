package com.ss.ers.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ErsAspect {
    
    @Before ("@annotation(com.ss.ers.aop.LogingAop)")
    public void startLog (JoinPoint joinPoint) {
        String className = joinPoint.getTarget ().getClass ().getSimpleName ();
        String methodName = joinPoint.getSignature ().getName ();
        
        log.info ("start " + className + " : " + methodName);
        
    }
    
    @AfterReturning ("@annotation(com.ss.ers.aop.LogingAop)")
    public void endLog (JoinPoint joinPoint) {
        String className = joinPoint.getTarget ().getClass ().getSimpleName ();
        String methodName = joinPoint.getSignature ().getName ();
        
        log.info ("end " + className + " : " + methodName);
        
    }
    
    @AfterThrowing (value = "@annotation(com.ss.ers.aop.LogingAop)", throwing = "ex")
    public void logingException (JoinPoint joinPoint, Throwable ex) {
        
        String className = joinPoint.getTarget ().getClass ().getSimpleName ();
        String methodName = joinPoint.getSignature ().getName ();
        
        log.info ("throwing " + className + " : " + methodName + " + " + ex.getClass ().getSimpleName () + " : "
                + ex.getLocalizedMessage ());
        
    }
    
}
