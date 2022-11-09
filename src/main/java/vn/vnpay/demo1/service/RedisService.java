package vn.vnpay.demo1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import vn.vnpay.demo1.domain.BankRequest;
import vn.vnpay.demo1.util.GsonUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
    private final JedisPool jedisPool;
    public boolean setData(BankRequest bankRequest) {
        Jedis jedis = null;
        try {
            log.info("Begin Set Data");
            jedis = jedisPool.getResource();
            log.info("Get pool success. Saving to Redis"); //todo check valid connection
            String payload = GsonUtils.convertJson().toJson(bankRequest);
            jedis.hset(bankRequest.getBankCode(), bankRequest.getTokenKey(), payload);
//            jedis.slaveof("10.22.22.21", 6379);
//            jedis.slaveof("10.22.22.22", 6379);
//
            log.info("End set hset redis success");
        } catch (Exception e) {
            log.error("Set data error", e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return true;
    }
}

//    public String getTokenKey(BankRequest bankRequest) {
//        log.info("Begin get token key");
//        Jedis jedis = null;
//        String tokenKey;
//        try {
//            log.info("Begin get data");
//            jedis = jedisPool.getResource();
//            log.info("Get pool success.");
//            tokenKey = jedis.hget(bankRequest.getBankCode(), bankRequest.getTokenKey());
//            log.info("Get redis expire time. Token key = {}", tokenKey);
//        } catch (Exception e) {
//            log.error(String.valueOf(e));
//            throw new RuntimeException(e);
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//        return tokenKey;
//    }
//
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//    @Value("${redis.maxtotal}")
//    private Integer maxTotal;
//    @Value("${redis.maxidle}")
//    private Integer maxIdle;
//    @Value("${redis.minidle}")
//    private Integer minIdle;
//
//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName(host);
//        configuration.setPort(port); // yaml
////        configuration.setDatabase(1);
//        configuration.setPassword("vnpayredis@123");
//        return new JedisConnectionFactory(configuration);
//    }
//
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
//}


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


