package priv.zzz.mscommon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import priv.zzz.examples.MyLog;
import priv.zzz.examples.MySlf4jLog;
import priv.zzz.utils.DateFormatter;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class MSCommonApplicationTests {

    @Resource
    MyLog myLog;

    @Resource
    MySlf4jLog mySlf4jLog;

    @Resource
    JedisPool jedisPool;

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

    @Test
    void jedisTest() {
        System.out.println(jedisPool.getResource().ping());
    }

    @Test
    void localDatetime() {
        System.out.println(new Date());
        System.out.println(DateFormatter.format(LocalDateTime.now()));
    }
}
