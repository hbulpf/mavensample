package dev.lpf.json.fastjson.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-18 21:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private int age;
    @JSONField(name = "sex",serializeUsing = SexSerializer.class,deserializeUsing = SexDeserializer.class)
    private boolean sex;
}
