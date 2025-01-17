package org.example.expert.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeLoggingAspect.class);

    @Around("@annotation(org.example.expert.domain.common.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object proceed = joinPoint.proceed();

        long executionTime = System.nanoTime() - start;
        logger.info("{} executed in {} ns ({} ms)",
            joinPoint.getSignature(),
            executionTime,
            executionTime / 1_000_000); // ns ms 동시 로깅

        return proceed;
    }

}
