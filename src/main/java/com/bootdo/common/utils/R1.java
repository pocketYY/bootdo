package com.bootdo.common.utils;

import java.util.HashMap;
import java.util.Map;

public class R1 extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static R1 error() {
        return error(1, "服务器内部错误");
    }

    public static R1 error(String msg) {
        return error(500, msg);
    }

    public static R1 error(int code, String msg) {
        R1 r = new R1();
        r.put("code", code);
        r.put("desc", msg);
        r.put("result","error");
        return r;
    }

    public static R1 ok(Object data) {
        R1 r = new R1();
        r.put("data", data);
        r.put("result","success");
        return r;
    }

    public static R1 okWithStringData(Object data) {
        R1 r = new R1();
        r.put("data", data);
        r.put("result","success");
        return r;
    }

    public static R1 ok(Map<String, Object> map) {
        R1 r = new R1();
        r.putAll(map);
        return r;
    }

    public static R1 ok() {
        return new R1();
    }

    @Override
    public R1 put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
