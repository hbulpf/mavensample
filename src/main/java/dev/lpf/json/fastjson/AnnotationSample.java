package dev.lpf.json.fastjson;

import com.alibaba.fastjson.JSON;
import dev.lpf.json.fastjson.entity.RspCode;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-18 15:49
 **/
public class AnnotationSample {
    public static void main(String[] args) {
        RspCode code = new RspCode("400","客户端错误");
        System.out.println("origin java Object: " + code);
        String rspStr = JSON.toJSONString(code);
        System.out.println("转换为json: "+rspStr);
        RspCode code2 = JSON.parseObject(rspStr,RspCode.class);
        System.out.println("json转回java Object: " + code2);
    }
}
