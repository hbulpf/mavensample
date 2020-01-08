package dev.lpf.json.nativejson;

import dev.lpf.json.jackson.demo.entity.FriendDetail;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-19 10:32
 **/
public class JSONSample {
    public static void main(String[] args) {
        FriendDetail friendDetail = new FriendDetail() {{
            this.setName("zhang");
            this.setAge(45);
            this.setUselessProp1("prop1");
            this.setUselessProp2(22);
        }};
    }

}
