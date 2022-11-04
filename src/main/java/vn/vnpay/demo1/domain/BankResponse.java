package vn.vnpay.demo1.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BankResponse {

    private String responseId;
    private Long responseTime;
    private String checkSum;
}
