package dev.lpf.json.jackson.official.entity;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-27 11:51
 **/
public class MyValue {
    public String name;
    public int age;
    // NOTE: if using getters/setters, can keep fields `protected` or `private`


    @Override
    public String toString() {
        return "MyValue{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public MyValue(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public MyValue() {
    }
}