package vn.vnpay.demo1.controller;

import org.slf4j.MDC;
import vn.vnpay.demo1.constant.ResponseData;
import vn.vnpay.demo1.domain.BankRequest;
import vn.vnpay.demo1.service.BankService;
import vn.vnpay.demo1.service.impl.BankServiceImpl;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class BankResource {

    private static final String TOKEN = "token";
    private final BankService bankService;
    public BankResource(BankServiceImpl bankService) {
        this.bankService = bankService;
    }

    @PostMapping()
    public ResponseData bankResponseEntity(@RequestBody @Valid BankRequest dto) {
//        String token = UUID.randomUUID().toString();
//        MDC.put(TOKEN, token);
        return ResponseData.ok(bankService.addDataToRedis(dto));
    }

}
