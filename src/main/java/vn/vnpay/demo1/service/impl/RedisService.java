package vn.vnpay.demo1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import vn.vnpay.demo1.domain.BankRequest;
import vn.vnpay.demo1.util.GsonUtils;


@EnableRedisRepositories
@RequiredArgsConstructor
@Slf4j
@Service
public class RedisService {
    private final JedisPool jedisPool;

    public void setData(BankRequest bankRequest) {
        Jedis jedis = null;
        try {
            log.info("Begin Set Data");
            jedis = jedisPool.getResource();
            log.info("Get pool success. Saving to Redis...");
            String payload = GsonUtils.convertJson().toJson(bankRequest);
            jedis.hset(bankRequest.getBankCode(), bankRequest.getTokenKey(), payload);
            log.info("Set redis expire time.");
        } catch (Exception e) {
            log.error("Set data error", e);
            throw new RuntimeException(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String getTokenKey(BankRequest bankRequest) {
        log.info("Begin get token key");
        Jedis jedis = null;
        String tokenKey;
        try {
            log.info("Begin get data");
            jedis = jedisPool.getResource();
            log.info("Get pool success.");
            tokenKey = jedis.hget(bankRequest.getBankCode(), bankRequest.getTokenKey());
            log.info("Get redis expire time. Token key = {}", tokenKey);
        } catch (Exception e) {
            log.error(String.valueOf(e));
            throw new RuntimeException(e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return tokenKey;
    }

}


//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName(localhost);
//        configuration.setPort(port); // yaml
//        configuration.setDatabase(database);
//        return new JedisConnectionFactory(configuration);
//    }

//    @Bean
//    public RedisTemplate<String, Object> createRedisTemplateForEntity() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory());
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

//    public void save(BankRequest bankRequest) {
//        Jedis jedis = null;
//        try {
//            log.info("Begin init.");
//            jedis = JedisConfiguration.getPool().getResource();
//            log.info("Get pool success. Saving to Redis...");
//            Gson gson = new Gson();
//            String bankRequestDTOJson = gson.toJson(bankRequest);
//            jedis.hset(bankRequest.getBankCode(),bankRequest.getTokenKey(),bankRequestDTOJson);
//            log.info("Set redis expire time.");
//
//        } catch (Exception e) {
//            log.error(String.valueOf(e));
//            throw new RuntimeException(e);
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//    }


