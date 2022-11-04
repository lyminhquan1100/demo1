package vn.vnpay.demo1.exception;

public class DataNotCorrectException extends RuntimeException {

    public DataNotCorrectException(String message){
        super(message);
    }
}
