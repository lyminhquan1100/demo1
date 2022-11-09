package vn.vnpay.demo1.domain;

import lombok.Data;

@Data
public class Bank {
    private String bankCode;
    private String privateKey;
    private String ips;
}
