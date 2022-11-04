package vn.vnpay.demo1.exception;

public class BankNotFoundException extends RuntimeException {

    public BankNotFoundException(String message){
        super(message);
    }

}
