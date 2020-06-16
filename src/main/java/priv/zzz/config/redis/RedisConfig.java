package priv.zzz.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxWaitMillis(-1);
        jedisPoolConfig.setMaxTotal(200);
        jedisPoolConfig.setMinIdle(0);
        return new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
    }

}
