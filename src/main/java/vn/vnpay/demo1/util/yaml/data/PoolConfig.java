package vn.vnpay.demo1.util.yaml.data;

import lombok.Data;

@Data
public class PoolConfig {
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer timeout;
}
