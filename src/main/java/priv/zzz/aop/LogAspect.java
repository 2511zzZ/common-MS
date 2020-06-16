package priv.zzz.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import priv.zzz.result.Result;
import priv.zzz.result.ResultSet;
import priv.zzz.utils.DateFormatter;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* priv.zzz.controller..*.*(..))")
    public void controllerAspect() {

    }

    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint){
        log.info(getRequestMessage(joinPoint));
    }

    @AfterReturning(pointcut = "controllerAspect()", returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue){
        if (returnValue instanceof Result){
            log.info(getResponseMessage(joinPoint, ((Result) returnValue).getStatus()));
        }
        if (returnValue instanceof ResultSet){
            log.info(getResponseMessage(joinPoint, ((ResultSet) returnValue).getStatus()));
        }
    }

    private String getRequestMessage(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        String[] parameters = methodSignature.getParameterNames();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Math.min(args.length, parameters.length); i++){
            stringBuilder.append(parameters[i]).append(":").append(args[i]).append(" ");
        }
        String params = "{ "+stringBuilder.toString()+"}";

        return this.getBaseMessage(joinPoint) + " " + params;
    }

    private String getResponseMessage(JoinPoint joinPoint, int status) {
        return this.getBaseMessage(joinPoint) + " " + status;
    }

    private String getBaseMessage(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes)(Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))).getRequest();
        String url = request.getRequestURI();
        String requestMethod = request.getMethod();
        String datetime = DateFormatter.format(LocalDateTime.now());

        return datetime + " " + url + " " + requestMethod;
    }
}