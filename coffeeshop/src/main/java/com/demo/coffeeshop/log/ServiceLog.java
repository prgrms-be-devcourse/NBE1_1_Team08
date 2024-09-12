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
public class ServiceLog {

    // 특정 패키지의 모든 서비스 메서드를 대상으로 로깅을 적용하는 Pointcut
    @Pointcut("within(com.demo.coffeeshop.service..*)")
    public void serviceMethods() {}

    // Around 어드바이스로 메서드 실행 전후 로깅
    @Around("serviceMethods()")
    public Object logServiceCall(ProceedingJoinPoint joinPoint) throws Throwable {
        String serviceName = joinPoint.getSignature().toShortString();  // 메서드 이름
        Object[] params = joinPoint.getArgs();  // 메서드 인자

        // 서비스 호출 전 로깅
        log.info("Service Call: {}", serviceName);
        log.debug("Parameters: {}", params);

        Object result;
        try {
            // 메서드 실행
            result = joinPoint.proceed();
        } catch (Exception e) {
            // 예외 발생 시 로깅
            log.error("Service {} threw an exception: {}", serviceName, e.getMessage());
            throw e;
        }

        // 서비스 결과 로깅
        log.info("Service {} result: {}", serviceName, result);
        return result;
    }
}