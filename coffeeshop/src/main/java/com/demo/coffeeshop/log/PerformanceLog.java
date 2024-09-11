package com.demo.coffeeshop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PerformanceLog {

    // 특정 패키지의 모든 메서드를 대상으로 성능 로깅을 적용하는 Pointcut
    @Pointcut("within(com.demo.coffeeshop.service..*)")
    public void serviceMethods() {}

    // Around 어드바이스로 메서드 실행 전후 시간을 측정하고 로깅
    @Around("serviceMethods()")
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();  // 시작 시간
        String operation = joinPoint.getSignature().toShortString();  // 메서드 이름

        try {
            // 메서드 실행
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();  // 종료 시간
            long duration = endTime - startTime;
            log.info("Operation: {} took {} ms", operation, duration);  // 성능 로그 출력
        }
    }
}
