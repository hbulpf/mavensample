package dev.lpf.json.fastjson.entity;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

import java.lang.reflect.Type;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-18 21:45
 **/
public class SexDeserializer implements ObjectDeserializer {
    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        String sex = parser.parseObject(String.class);
        if ("男".equals(sex)) {
            return (T) Boolean.TRUE;
        } else {
            return (T) Boolean.FALSE;
        }
    }

    @Override
    public int getFastMatchToken() {
        return JSONToken.UNDEFINED;
    }
}
