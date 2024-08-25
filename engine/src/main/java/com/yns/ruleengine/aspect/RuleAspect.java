package com.yns.ruleengine.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class RuleAspect {

    //@Around("@annotation(com.yns.ruleengine.aspect.LogExecutionTime)")
    @Around("execution(public boolean execute(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println("Exiting method: "
                + joinPoint.getSignature() + " at "
                + endTime + " with execution time: "
                + (endTime - startTime) + " ms");

        return proceed;
    }
}
