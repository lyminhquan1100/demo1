package vn.vnpay.demo1.service;

import vn.vnpay.demo1.domain.BankRequest;
import vn.vnpay.demo1.domain.BankResponse;

public interface BankService {
    BankResponse addDataToRedis(BankRequest bankRequest);

}
