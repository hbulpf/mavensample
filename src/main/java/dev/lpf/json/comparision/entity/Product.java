package dev.lpf.json.comparision.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

    /*
    productName set,get注解相同, fastjson ok,jackson ok
    desc 替换大小写 , fastjson ok,jackson 不ok
    price 用另一个名字 , fastjson ok,jackson ok
    结论: fastjson忽略大小写 ， jackson 不忽略大小写
    */
    private String productName;
    private String desc;
    private String price;


    @JsonProperty("name")
    @JSONField(name="name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("name")
    @JSONField(name="name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    @JsonProperty("desc")
    @JSONField(name="desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("DESC")
    @JSONField(name="DESC")  //测试代码中对jsonStr有一个toUpperCase的操作。就会这与"DESC"匹配
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("price")
    @JSONField(name="price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price2")
    @JSONField(name="price2")
    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
            "productName='" + productName + '\'' +
            ", desc='" + desc + '\'' +
            ", price='" + price + '\'' +
            '}';
    }
}