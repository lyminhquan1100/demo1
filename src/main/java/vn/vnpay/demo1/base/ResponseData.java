package vn.vnpay.demo1.base;

import lombok.Data;
import vn.vnpay.demo1.config.Message;

import java.util.UUID;


@Data
public class ResponseData {

    private String code;

    private String message;

    private Object data;

    public static ResponseData restResult(Object data) {
        ResponseData apiResult = new ResponseData();
        apiResult.setCode(Message.CODE_SUCCESS);
        apiResult.setMessage(Message.SUCCESS);
        apiResult.setData(data);
        return apiResult;
    }
    public static ResponseData ok(Object data) {
        return restResult(data);
    }

}
