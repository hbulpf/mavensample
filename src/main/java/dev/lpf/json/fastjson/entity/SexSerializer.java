package dev.lpf.json.fastjson.entity;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-18 21:29
 **/

public class SexSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Boolean value = (Boolean) object;
        String text = "女";
        if (value != null && value == true) {
            text = "男";
        }
        serializer.write(text);
    }
}
