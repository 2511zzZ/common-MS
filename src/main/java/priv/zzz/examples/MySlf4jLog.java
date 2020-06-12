package priv.zzz.examples;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MySlf4jLog {
    public void loggerTest(){

        // 使用@Slf4j注解
        log.trace("logger trace test");
        log.debug("logger debug test");
        log.info("logger info test");
        log.warn("logger warn test");
        log.error("logger error test");

        // 使用占位符的形式编写日志
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        Date now = new Date();
        log.info("{} : {} method invoke", now, methodName);
    }
}
