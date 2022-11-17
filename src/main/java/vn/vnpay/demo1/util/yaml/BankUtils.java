package vn.vnpay.demo1.util.yaml;

import lombok.extern.slf4j.Slf4j;
import vn.vnpay.demo1.domain.Bank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@Slf4j
@ConfigurationProperties(prefix = "demo1")
public class BankUtils {
    private List<Bank> banks;
    public Bank findByBankCode(String bankCode) {
        /** Đặt tên rõ nghĩa, biến nào có khả năng ít null nhất sẽ để trước) */
        log.info("Begin find by bank code: bank code = {}", bankCode);
        List<Bank> bankList = banks.stream().filter(bank -> bankCode.equals(bank.getBankCode()))
                .collect(Collectors.toList());
        if (!bankList.isEmpty()) {
            log.info("Found bank ");
            return bankList.get(0);
        }
        log.info("End process, don't find bank");
        return null;
    }

}
