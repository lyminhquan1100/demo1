package vn.vnpay.demo1.exception;

import vn.vnpay.demo1.config.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(),
                error.getDefaultMessage()));
        return new ErrorResponse(Message.CODE_IS_VALID, errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BankNotFoundException.class)
    public ErrorResponse handleBusinessException(BankNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Message.BANK_CODE, ex.getMessage());
        return new ErrorResponse(Message.CODE_BAD_REQUEST, errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataNotCorrectException.class)
    public ErrorResponse handleBusinessException(DataNotCorrectException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Message.DATA, ex.getMessage());
        return new ErrorResponse(Message.CODE_BAD_REQUEST, errorMap);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClassAlreadyExistException.class)
    public ErrorResponse handleBusinessException(ClassAlreadyExistException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Message.TOKEN_KEY, ex.getMessage());
        return new ErrorResponse(Message.CODE_BAD_REQUEST, errorMap);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(Message.MESSAGE, ex.getMessage());
        return new ErrorResponse(Message.CODE_BAD_REQUEST, errorMap);
    }
}
