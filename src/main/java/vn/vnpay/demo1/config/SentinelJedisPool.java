package vn.vnpay.demo1.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;
import vn.vnpay.demo1.util.yaml.RedisUtils;
import vn.vnpay.demo1.util.yaml.data.PoolConfig;
import vn.vnpay.demo1.util.yaml.data.RedisSentinel;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class SentinelJedisPool {

    //todo thread save

    private final RedisUtils redisUtils;

    public SentinelJedisPool(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Bean
    public JedisSentinelPool createJedisPool() {
        log.info("Jedis pool config");
        JedisPoolConfig config = new JedisPoolConfig();
        PoolConfig poolConfig = redisUtils.getPoolConfig();
        log.info("Set max total : {}", poolConfig.getMaxTotal());
        config.setMaxTotal(poolConfig.getMaxTotal());
        log.info("Set max idle : {}", poolConfig.getMaxIdle());
        config.setMaxIdle(poolConfig.getMaxIdle());
        log.info("Set min idle : {}", poolConfig.getMinIdle());
        config.setMinIdle(poolConfig.getMinIdle());

        RedisSentinel redisSentinel = redisUtils.getRedisSentinel();
        Set<String> sentinels = new HashSet<>(redisUtils.getRedisSentinel().getNodes());
        return new JedisSentinelPool(redisSentinel.getMaster(), sentinels, config, poolConfig.getTimeout(),
                redisSentinel.getPassword());
    }

//    private Set<String> getSentinels() {
//        Set<String> sentinels = new HashSet<>();
//        log.info("Host 1 : {} : {}", host1, port);
//        sentinels.add(new HostAndPort(host1, port).toString());
//        log.info("Host 2 : {} : {}", host2, port);
//        sentinels.add(new HostAndPort(host2, port).toString());
//        log.info("Host 3 : {} : {}", host3, port);
//        sentinels.add(new HostAndPort(host3, port).toString());
//        return sentinels;
//    }
}


