package vn.vnpay.demo1.util;

import com.google.gson.Gson;

public class GsonUtils {
    private static Gson gson = null;

    private GsonUtils() {

    }

    public static Gson convertJson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

}

