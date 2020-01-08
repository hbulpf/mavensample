package dev.lpf.json.fastjson.serializable;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-18 22:15
 **/
public class BaseDTO implements Serializable {
    private static final long  serialVersionUID = 2230553030766621644L;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
