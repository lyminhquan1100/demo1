package vn.vnpay.demo1.util.yaml;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import vn.vnpay.demo1.util.YamlPropertySourceFactory;
import vn.vnpay.demo1.util.yaml.data.PoolConfig;
import vn.vnpay.demo1.util.yaml.data.RedisSentinel;

@Component
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
@PropertySource(value = "classpath:application.yaml", factory = YamlPropertySourceFactory.class)
public class RedisUtils {
    private RedisSentinel redisSentinel;
    private PoolConfig poolConfig;

}