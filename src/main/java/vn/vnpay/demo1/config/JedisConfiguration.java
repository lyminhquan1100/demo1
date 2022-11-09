package vn.vnpay.demo1.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
@Data
public class JedisConfiguration {
    //    private static JedisPool jedisPool;
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.max-total}")
    private Integer maxTotal;
    @Value("${redis.max-idle}")
    private Integer maxIdle;
    @Value("${redis.min-idle}")
    private Integer minIdle;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        log.info("Config pool");
        poolConfig.setMaxTotal(maxTotal);
        log.info("Set max total : {}", maxTotal);
        poolConfig.setMaxIdle(maxIdle);
        log.info("Set max idle : {}", maxIdle);
        poolConfig.setMinIdle(minIdle);
        log.info("Set min idle : {}", minIdle);
        return new JedisPool(poolConfig, host, port, 123123, "vnpayredis@123", 1);
    }

//    @Bean
//    public RedisConnectionFactory jedisConnectionFactory() {
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//                .master("10.22.22.20")
//                .sentinel("10.22.22.21", 26579)
//                .sentinel("10.22.22.22", 26580);
//        return new JedisConnectionFactory(sentinelConfig);
//    }

}




