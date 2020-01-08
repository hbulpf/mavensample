package dev.lpf.json.jackson.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import dev.lpf.json.jackson.demo.entity.Friend;
import dev.lpf.json.jackson.demo.entity.FriendDetail;
import dev.lpf.json.jackson.demo.entity.Person;
import dev.lpf.json.jackson.demo.entity.Student;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonSample {
    public static void main(String[] args) throws IOException {
        System.out.println("---------简单的映射---------");
        quickStart();
        System.out.println("---------集合的映射---------");
        collectionMapping();
        System.out.println("---------注解---------");
        annotationMapping();
        System.out.println("---------java8日期支持---------");
        java8DateTime();
        System.out.println("---------复杂对象支持---------");
        complexJavaObject();

    }

    static void quickStart() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Friend friend = new Friend(){
            {
                this.setNickname("yitian");
                this.setAge(25);
            }
        };

        System.out.println(friend);
        //mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 写为字符串
        String text = mapper.writeValueAsString(friend);
        // 写为文件
        mapper.writeValue(new File("friend.json"), friend);
        // 写为字节流
        byte[] bytes = mapper.writeValueAsBytes(friend);
        System.out.println(text);
        // 从字符串中读取
        Friend newFriend = mapper.readValue(text, Friend.class);
        // 从字节流中读取
        newFriend = mapper.readValue(bytes, Friend.class);
        // 从文件中读取
        newFriend = mapper.readValue(new File("friend.json"), Friend.class);
        System.out.println(newFriend);
    }

    static void collectionMapping() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("name", "yitian");
        map.put("interests", new String[]{"pc games", "music"});

        String text = mapper.writeValueAsString(map);
        System.out.println(text);

        Map<String, Object> map2 = mapper.readValue(text, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map2);

        JsonNode root = mapper.readTree(text);
        String name = root.get("name").asText();
        int age = root.get("age").asInt();

        System.out.println("name:" + name + " age:" + age);
    }

    static void annotationMapping() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        FriendDetail fd = new FriendDetail("yitian", 25, "", 0, "");
        String text = mapper.writeValueAsString(fd);
        System.out.println(text);

        FriendDetail fd2 = mapper.readValue(text, FriendDetail.class);
        System.out.println(fd2);

    }

    static void java8DateTime() throws IOException {
        Person p1 = new Person("yitian", "易天", 25, "10000", LocalDate.of(1994, 1, 1),new ArrayList<String>(){
            {
                this.add("painting");
                this.add("piano");
                this.add("hiving");
            }
        });
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String text = mapper.writeValueAsString(p1);
        System.out.println(text);

        Person p2 = mapper.readValue(text, Person.class);
        System.out.println(p2);
    }

    static void complexJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Friend> friendList = new ArrayList<>();
        friendList.add(new Friend("zhangming",12));
        friendList.add(new Friend("Hangzhong",14));
        friendList.add(new Friend("SanYixian",17));
        Student stu1 = new Student("23222",21,friendList.toArray(new Friend[0]));
        System.out.println("stu1: " + stu1);
        String text = mapper.writeValueAsString(stu1);
        System.out.println(text);
        Student stu2 = mapper.readValue(text,Student.class);
        System.out.println("stu2: " + stu2);
    }
}
