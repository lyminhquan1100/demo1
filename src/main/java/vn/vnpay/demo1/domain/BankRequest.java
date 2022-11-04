package vn.vnpay.demo1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRequest implements Serializable {
    @NotBlank(message = "tokenKey may not be blank")
    @NotNull(message = "tokenKey may not be null")
    private String tokenKey;

    @NotBlank(message = "apiID may not be blank")
    @NotNull(message = "apiID may not be null")
    private String apiID;

    @NotBlank(message = "mobile may not be blank")
    @NotNull(message = "mobile may not be null")
    private String mobile;

    @NotBlank(message = "bankCode may not be blank")
    @NotNull(message = "bankCode may not be null")
    private String bankCode;

    @NotBlank(message = "accountNo may not be blank")
    @NotNull(message = "accountNo may not be null")
    private String accountNo;

    @NotBlank(message = "payDate may not be blank")
    @NotNull(message = "payDate may not be null")
    private String payDate;

    @NotBlank(message = "additionalData may not be blank")
    @NotNull(message = "additionalData may not be null")
    private String additionalData;

    @NotBlank(message = "debitAmount may not be blank")
    @NotNull(message = "debitAmount may not be null")
    private String debitAmount;

    @NotBlank(message = "respCode may not be blank")
    @NotNull(message = "respCode may not be null")
    private String respCode;

    @NotBlank(message = "respDesc may not be blank")
    @NotNull(message = "respDesc may not be null")
    private String respDesc;

    @NotBlank(message = "traceTransfer may not be blank")
    @NotNull(message = "traceTransfer may not be null")
    private String traceTransfer;

    @NotBlank(message = "messageType may not be blank")
    @NotNull(message = "messageType may not be null")
    private String messageType;

    @NotBlank(message = "checkSum may not be blank")
    @NotNull(message = "checkSum may not be null")
    private String checkSum;

    @NotBlank(message = "orderCode may not be blank")
    @NotNull(message = "orderCode may not be null")
    private String orderCode;

    @NotBlank(message = "userName may not be blank")
    @NotNull(message = "userName may not be null")
    private String userName;

    @NotBlank(message = "realAmount may not be blank")
    @NotNull(message = "realAmount may not be null")
    private String realAmount;

    @NotBlank(message = "promotionCode may not be blank")
    @NotNull(message = "promotionCode may not be null")
    private String promotionCode;
}
