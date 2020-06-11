package priv.zzz.mscommon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import priv.zzz.examples.MyLog;
import priv.zzz.examples.MySlf4jLog;

import javax.annotation.Resource;

@SpringBootTest
class MSCommonApplicationTests {

    @Resource
    MyLog myLog;

    @Resource
    MySlf4jLog mySlf4jLog;

    @Test
    void contextLoads() {
    }

    @Test
    void sout() {
        System.out.println("test");
    }

    @Test
    void myLog() {
        myLog.loggerTest();
    }
    @Test
    void mySlf4jLog() {
        mySlf4jLog.loggerTest();
    }
}
