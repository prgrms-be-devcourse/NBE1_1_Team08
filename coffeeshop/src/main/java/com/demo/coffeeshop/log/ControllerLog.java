package com.demo.coffeeshop.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerLog {

    @Pointcut("within(com.demo.coffeeshop.controller..*)")
    public void controllerMethods() {}

    // Controller 메서드 실행 전에 logRequest 호출
    @Before("controllerMethods() && args(request,..)")
    public void logRequest(HttpServletRequest request) {
        log.info("Request Method: {}, URI: {}", request.getMethod(), request.getRequestURI());
        log.debug("Request Headers: {}", request.getHeaderNames());
        log.debug("Request Parameters: {}", request.getParameterMap());
    }

    // Controller 메서드 실행 후에 logResponse 호출
    @AfterReturning(pointcut = "controllerMethods()", returning = "response")
    public void logResponse(ResponseEntity<?> response) {
        log.info("Response Status: {}", response.getStatusCode());
        log.debug("Response Body: {}", response.getBody());
    }
}
