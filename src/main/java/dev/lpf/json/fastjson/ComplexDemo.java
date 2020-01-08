package dev.lpf.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-17 22:25
 **/
public class ComplexDemo {

    public static void main(String[] args) {
        stringToJson("");
    }
    public static void stringToJson(String s) {

        System.out.println("---fastjson的简单使用Demo---");

        // 将字符串解析成json对象
        JSONObject js = JSON.parseObject(s);

        System.out.println("---获取属性值---");
        // 获取需要的属性值
        Object content = js.get("content");
        System.out.println("Object content：" + content);
        // 属性值一般为字符串，所以可以直接强转
        String content2 = (String) js.get("content");
        System.out.println("String scontent:" + content2);
        // 如果获取的属性值仍然为json格式，则用JSONObeject类型
        JSONObject author = (JSONObject) js.get("author");
        System.out.println("JSONObject author:" + author);
        // 如下方法和上述方法效果一样
        JSONObject author2 = js.getJSONObject("author");
        System.out.println("JSONObject author2" + author2);
        // toString()方法获取的值是一样的，只不过把数据类型转换成了String
        String AuthorToString = author.toString();
        System.out.println("String AuthorToString:" + AuthorToString);
        // 要获取author的json里的属性值，方法相同
        // 需要注意的是获取属性值，必须是JSONObejet类型的对象
        Object nickName = author.get("nickName");
        System.out.println("Object nickName:" + nickName);
        String nickName2 = (String) author.get("nickName");
        System.out.println("String nickName2:" + nickName2);
        String nickName3 = nickName.toString();
        System.out.println("String nickName3:" + nickName3);
        System.out.println("---json属性值的获取Demo完毕---");
        System.out.println("");
        System.out.println("");

        System.out.println("---插入值---");
        //想在哪个json对象插入数值，就用如下方法，全局json数据都会有效。
        System.out.println("在author里插入｛\"来源\":\"新浪微博\"｝");
        author.fluentPut("来源", "新浪微博");
        System.out.println("局部json："+author);
        System.out.println("全局json："+js);
        System.out.println("全局里插入｛\"地点\":\"北京市朝阳区来广营\"｝");
        js.fluentPut("地点", "北京市朝阳区来广营");
        System.out.println("全局json："+js);
        System.out.println("---插值Demo完毕---");
        System.out.println("");
        System.out.println("");

        System.out.println("---删除值---");
        //要删除某个属性值用如下方法
        js.remove("author");
        System.out.println("删除author："+js);
        js.remove("地点");
        System.out.println("删除地点："+js);
        System.out.println("---删除值Demo完毕---");
        System.out.println("");
        System.out.println("");


        System.out.println("---修改值---");
        System.out.println("还是先插入一个地点值，方便演示");
        System.out.println("全局里插入｛\"地点\":\"北京市朝阳区来广营\"｝");
        js.fluentPut("地点", "北京市朝阳区来广营");
        System.out.println("全局json："+js);
        //用下列方法修改值
        js.replace("地点", "河北省石家庄市裕华区槐安东路");
        System.out.println("修改地点信息后："+js);
        System.out.println("---修改值Demo完毕---");
        System.out.println("");
        System.out.println("");

    }
}
