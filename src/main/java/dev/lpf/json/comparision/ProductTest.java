package dev.lpf.json.comparision;


import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lpf.json.comparision.entity.Product;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * 对fastjson,jackson 注解加在方法上做个测试
 */
public class ProductTest {

    private static Logger logger = Logger.getLogger("ProductTest");
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Product product = new Product();
        product.setProductName("产品");
        product.setDesc("这是一个产品");
        product.setPrice("22.3");
        System.out.println(product);

        System.out.println("==================== fastjson ====================");
        String jsonStr = JSONObject.toJSONString(product);
        System.out.println("转换为json:" + jsonStr);
        // jsonStr = jsonStr.toUpperCase();
        jsonStr = jsonStr.replace("price","price2");
        System.out.println("替换属性: " + jsonStr);
        product = JSONObject.parseObject(jsonStr, Product.class);
        System.out.println("json 转为 java object: " + product.toString());

        System.out.println("==================== jackson ====================");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr2 = null;
        try {
            jsonStr2 = objectMapper.writeValueAsString(product);
            System.out.println("转换为json: " + jsonStr2);
        } catch (JsonProcessingException e) {
            logger.severe("cast Java Object to JSON Error : " + e.getMessage());
        }
        // jsonStr = jsonStr.toUpperCase();
        jsonStr2 = jsonStr2.replace("price","price2");
        System.out.println("替换属性: " + jsonStr2);
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            product = objectMapper.readValue(jsonStr2, Product.class);
            System.out.println("json转为 java object: " + product.toString());
        } catch (IOException e) {
            logger.severe("resolve json Error : " + e.getMessage());
        }

    }

    //logger 总结 https://blog.csdn.net/lll_90/article/details/92428429
}