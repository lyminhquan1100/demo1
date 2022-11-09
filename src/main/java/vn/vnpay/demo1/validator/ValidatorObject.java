package vn.vnpay.demo1.validator;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.vnpay.demo1.config.Message;
import vn.vnpay.demo1.exception.BankNotFoundException;

import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@Component
public class ValidatorObject {
    public ValidatorObject validAllFields(Object object) {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        try {
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                Object o = declaredField.get(object);
                if (o instanceof String) {
                    String fieldValue = (String) o;
                    validField(fieldValue);
                } else if (o instanceof List && CollectionUtils.isEmpty((List) o)) {
                    throw new BankNotFoundException(Message.BANK_NOT_FOUND);
                } else if (o == null) {
                    throw new BankNotFoundException(Message.BANK_NOT_FOUND);
                }
            }
        } catch (IllegalAccessException e) {
            log.error("validAllFields: {}", e.getMessage());
        }
        return this;
    }

    public ValidatorObject validField(String toValid) {
        if (ObjectUtils.isEmpty(toValid)) {
            throw new BankNotFoundException(Message.BANK_NOT_FOUND);
        }
        return this;
    }
}
