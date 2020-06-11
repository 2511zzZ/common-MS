package priv.zzz.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyLog {
    public void loggerTest(){
        Logger logger = LoggerFactory.getLogger(MyLog.class);
        logger.trace("logger trace test");
        logger.debug("logger debug test");
        logger.info("logger info test");
        logger.warn("logger warn test");
        logger.error("logger error test");
    }
}
