package vn.vnpay.demo1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import vn.vnpay.demo1.config.Message;
import vn.vnpay.demo1.domain.Bank;
import vn.vnpay.demo1.domain.BankRequest;
import vn.vnpay.demo1.domain.BankResponse;
import vn.vnpay.demo1.exception.BankNotFoundException;
import vn.vnpay.demo1.exception.DataNotCorrectException;
import vn.vnpay.demo1.service.BankService;
import vn.vnpay.demo1.service.RedisService;
import vn.vnpay.demo1.util.BankUtils;
import vn.vnpay.demo1.util.GsonUtils;

import java.util.UUID;

// vn.vnpay, them interface
@Slf4j
@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {
    private final RedisService redisService;
    private final BankUtils bankUtils;
    private final RedisTemplate<String,?> redis1;

    private final RedisTemplate<String,?> redis2;
    @Override
    public BankResponse addDataToRedis(BankRequest bankRequest) { // save?
        log.info("Begin add Data To Redis : {}", bankRequest);// Them log begin
        Bank bank = bankUtils.findByBankCode(bankRequest.getBankCode()); //bankname?
        if (ObjectUtils.isEmpty(bank)) {
            log.info("Bank not found"); // error ->info // ghi log rõ data
            throw new BankNotFoundException(Message.BANK_NOT_FOUND);
        }
        if (ObjectUtils.isEmpty(bank.getPrivateKey())) {
            log.info("Private key not exist");
            throw new BankNotFoundException(Message.PRIVATE_KEY_NOT_EXIST);
        }
        String checkSum = getCheckSum(bankRequest, bank.getPrivateKey());
        String checkSumSha256 = DigestUtils.sha256Hex(checkSum);
        log.info("Check sum convert to SHA256 = {}", checkSumSha256);
        if (!bankRequest.getCheckSum().equalsIgnoreCase(checkSumSha256)) { //return, throw first
            log.info("Data not correct");
            throw new DataNotCorrectException(Message.DATA_NOT_CORRECT);
        }
        log.info("Check sum is correct");
        String payload1 = GsonUtils.convertJson().toJson(bankRequest);
        redis1.opsForHash().put(bankRequest.getBankCode(),bankRequest.getTokenKey(),payload1);
        boolean result = redisService.setData(bankRequest);
        if (!result) {
            log.info("Save token to redis false !");
            throw new RuntimeException(Message.DATA_SET_ERROR); //todo loi he thong
        }
        log.info("BankService up to Redis : hash = {}, key = {}", bankRequest.getBankCode(),
                bankRequest.getTokenKey());
        return getCheckSumResponseSHA256(bank.getPrivateKey()); // bo VM
    }

    private String getCheckSum(BankRequest bankRequest, String privateKey) { // xoa static
        log.info("Begin get check sum with : bankRequest = {}, privateKey = {}", bankRequest, privateKey); // ghi log ro khong cach
        StringBuilder checkSum = new StringBuilder();
        checkSum.append(bankRequest.getMobile()).append(bankRequest.getBankCode())
                .append(bankRequest.getAccountNo())
                .append(bankRequest.getPayDate()).append(bankRequest.getDebitAmount())
                .append(bankRequest.getRespCode())
                .append(bankRequest.getTraceTransfer()).append(bankRequest.getMessageType()).append(privateKey);
        log.info("End process, return check sum = {}", checkSum);
        return checkSum.toString();
    }
//todo connect 3 redis..., exception(truyen ca code vao)
    private BankResponse getCheckSumResponseSHA256(String privateKey) { // check?
        log.info("Begin get check sum response");
        String id = UUID.randomUUID().toString();
        Long time = System.currentTimeMillis();
        StringBuilder checkSumResponse = new StringBuilder();
        checkSumResponse.append(Message.CODE_SUCCESS).append(Message.SUCCESS)
                .append(id)
                .append(time)
                .append(privateKey);
        log.info("Return check sum response, checkSumResponse = {}", checkSumResponse);
        String checkSumResponseSha256 = DigestUtils.sha256Hex(String.valueOf(checkSumResponse)); // check?
        log.info("End process, checkSumResponseSH256 = {}", checkSumResponseSha256);
        return BankResponse.builder()
                .responseId(id)
                .responseTime(time)
                .checkSum(checkSumResponseSha256)
                .build();
    }



}
