package com.traning.ecommerce.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//Day 26: Aspect-Oriented Programming (AOP)
@Component //(so Spring registers it as a Bean).
@Aspect //(so Spring knows it has AOP superpower rules)
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // The @Around annotation tells Spring to run this code BEFORE and AFTER a method.
    // The string inside execution(...) is the Pointcut.
    // Rule: * (any return type) com.traning.ecommerce.Services.* (any class) .* (any method) (..) (any arguments)
    @Around("execution(* com.traning.ecommerce.Services.*.*(..))")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1. WHAT TO DO BEFORE THE METHOD RUNS:
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        // 2. LET THE ACTUAL METHOD RUN!
        Object result = joinPoint.proceed();

        // 3. WHAT TO DO AFTER THE METHOD FINISHES:
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        logger.info("[Performance Tracker] {}.{} executed in {} ms", className, methodName, executionTime);

        // 4. Return the result back to the Controller
        return result;
    }
}