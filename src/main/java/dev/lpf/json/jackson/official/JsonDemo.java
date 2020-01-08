package dev.lpf.json.jackson.official;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.lpf.json.jackson.official.entity.MyValue;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

/**
 * @program: gradle-demo-1
 * @description: jackson 官方 demo
 * @author: Li Pengfei
 * @create: 2019-12-27 11:35
 **/
public class JsonDemo {
    private static final Logger LOG = Logger.getLogger("JsonDemo");

    public static void main(String[] args) {
        try {
            pojo2json();
            map2json();
            list2json();
        } catch (IOException e) {
            LOG.severe("pojos2json 错误");
            e.printStackTrace();
        }
    }

    public static void pojo2json() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse

        MyValue myObject = new MyValue();
        myObject.age = 12;
        myObject.name = "zhang";

        System.out.println("----------序列化为json-----------");
        mapper.writeValue(new File("result.json"), myObject);
        // or:
        byte[] jsonBytes = mapper.writeValueAsBytes(myObject);
        // or:
        String jsonString = mapper.writeValueAsString(myObject);

        System.out.println(Arrays.toString(jsonBytes));
        System.out.println(jsonString);

        System.out.println("----------json反序列化为Java对象-----------");
        MyValue value = mapper.readValue(new File("result.json"), MyValue.class);
        System.out.println(value);
        // or:
        // value = mapper.readValue(new URL("http://some.com/api/entry.json"), MyValue.class);
        // or:
        value = mapper.readValue("{\"name\":\"Bob\", \"age\":13}", MyValue.class);
        System.out.println(value);
    }

    public static void map2json() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        Map map = new HashMap<String, Integer>();
        map.put("zhang", 13);
        map.put("chen", 23);
        map.put("lan", 15);

        System.out.println("---------- Map 序列化为 json -----------");
        String jsonSource = mapper.writeValueAsString(map);
        System.out.println(jsonSource);

        System.out.println("----------json反序列化为 Map -----------");
        Map<String, Integer> scoreByName = mapper.readValue(jsonSource, Map.class);
        System.out.println(scoreByName);

        System.out.println("================= Map 复杂 value =================");

        map = new HashMap<String, Integer>();
        map.put("zhang", 13);
        map.put("chen", 23);
        map.put("lan", 15);

        System.out.println("---------- Map 序列化为 json -----------");
        jsonSource = mapper.writeValueAsString(map);
        System.out.println(jsonSource);

        System.out.println("----------json反序列化为 Map -----------");
        Map<String, Integer> objects = mapper.readValue(jsonSource, Map.class);
        System.out.println(objects);

    }

    public static void list2json() throws IOException {
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        List list = new ArrayList();
        list.add(new MyValue("Hanhan",22));
        list.add(new MyValue("Zhang YiMing",36));
        list.add(new MyValue("Ma HuaTeng",47));

        System.out.println("---------- List 序列化为 json -----------");
        String jsonSource = mapper.writeValueAsString(list);
        System.out.println(jsonSource);

        System.out.println("----------json反序列化为 List -----------");
        List<MyValue> names = mapper.readValue(jsonSource, List.class);
        System.out.println(names);
    }
}
