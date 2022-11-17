package vn.vnpay.demo1.util.yaml.data;

import lombok.Data;

import java.util.List;
@Data
public class RedisSentinel {
    private String master;
    private String password;
    private List<String> nodes;

}
