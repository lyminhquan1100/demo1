package vn.vnpay.demo1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRedis implements Serializable {

    private String bankCode;
    private int tokenKey;
    private BankRequest bankRequest;
}
