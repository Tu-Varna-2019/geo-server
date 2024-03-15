package com.tuvarna.geo.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());

    @AfterReturning(pointcut = "execution(* com.tuvarna.geo.service..*.*(..))", returning = "returnValue")
    public void afterReturn(JoinPoint joinPoint, Object returnValue) {
        logger.info("{} returned with value {}", joinPoint.getSignature(), returnValue);
    }

    @AfterThrowing(pointcut = "execution(* com.tuvarna.geo.service..*.*(..))", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("Exception in {} with cause = '{}'", joinPoint.getSignature(), ex.getMessage(), ex);
    }
}
