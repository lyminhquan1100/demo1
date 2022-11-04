package vn.vnpay.demo1.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Data
public class JedisConfiguration {
    private static JedisPool jedisPool;
    @Value("${redis.host}")
    private static String host;
    @Value("${redis.port}")
    private static Integer port;
    @Value("${redis.max-total}")
    private static Integer maxTotal;
    @Value("${redis.max-idle}")
    private static Integer maxIdle;
    @Value("${redis.min-idle}")
    private static Integer minIdle;

    @Bean
    private static JedisPool jedisPool() {
        if (jedisPool == null) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(maxTotal);
            poolConfig.setMaxIdle(maxIdle);
            poolConfig.setMinIdle(minIdle);
            JedisConfiguration.jedisPool = new JedisPool(poolConfig, host, port);
        }
        return jedisPool;
    }
}


