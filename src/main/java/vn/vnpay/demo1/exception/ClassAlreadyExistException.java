package vn.vnpay.demo1.exception;

public class ClassAlreadyExistException extends RuntimeException {

    public ClassAlreadyExistException(String message){
        super(message);
    }
}
