
package dev.lpf.json.jackson.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import dev.lpf.json.jackson.demo.entity.Friend;
import dev.lpf.json.jackson.demo.entity.Student;
import dev.lpf.json.jackson.demo.entity.Worker;

/**
 * jackson 复杂对象支持 Demo
 */
public class ComplexSample {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            //            complexJavaObject();
            readFromJsonList();
            readFromRspJson();
        } catch (IOException e) {
            System.out.println("JSON Serializable Error: " + e.getMessage());
        }
    }

    /**
     * Complex java object.
     *
     * @throws IOException the io exception
     */
    static void complexJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Friend> friendList = new ArrayList<>();
        friendList.add(new Friend("zhangming", 12));
        friendList.add(new Friend("Hangzhong", 14));
        friendList.add(new Friend("SanYixian", 17));
        Student stu1 = new Student("23222", 21, friendList.toArray(new Friend[0]));
        System.out.println("stu1: " + stu1);

        // 写为字符串
        String text = mapper.writeValueAsString(stu1);
        System.out.println(text);

        // 从字符串中读取
        Student stu2 = mapper.readValue(text, Student.class);
        System.out.println("stu2: " + stu2);
    }

    /**
     * Read to List from json list.
     *
     * @throws JsonProcessingException the json processing exception
     */
    static void readFromJsonList() throws JsonProcessingException {
        String str = "{\"name\":\"yitian\",\"interests\":[\"pc games\",\"music\"],\"age\":25,\"friends\":[{\"name\":\"hanhan\",\"interests\":[\"123\",\"223\"],\"age\":25},{\"name\":\"zhengxiao\",\"interests\":[\"452\",\"music\"],\"age\":10},{\"name\":\"chenxixi\",\"interests\":[\"111\",\"chess\"],\"age\":25}]}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);
        String strName = root.get("name").asText();
        String strInterests = root.get("interests").asText();
        ArrayNode hobbiesNode = (ArrayNode)root.get("interests");
        System.out.printf("=>hobbies:\t");
        for(JsonNode node : hobbiesNode){
            System.out.printf("%s\t",node);
        }
        System.out.println();

        String strAge = root.get("age").asText();
        String strFriends = root.get("friends").asText();
        System.out.println("\tstrName:"+strName);
        System.out.println("\tstrInterests:"+strInterests);
        System.out.println("\tstrAge:"+strAge);
        System.out.println("\tstrFriends:"+strFriends);
        System.out.println("\tname:"+root.get("name"));
        System.out.println("\tinterests"+root.get("interests"));
        System.out.println("\tage"+root.get("age"));
        System.out.println("\tfriends"+root.get("friends"));
        System.out.println("worker list:");
        List<Worker> workerList = new ArrayList<>();
        if(root.get("friends").isArray()){
            for(JsonNode node : root.get("friends")){
                workerList.add(mapper.treeToValue(node,Worker.class));
            }
        }
        System.out.println(workerList.toString());
    }

    /**
     * 从Json返回体中读取内容
     *
     */
    static void readFromRspJson() throws JsonProcessingException {
        String str = "{\"code\":\"0\",\"data\":{\"type\":1}}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(str);
        int code = root.get("code").asInt();
        int type = root.get("data").get("type").asInt();
        System.out.println(String.format("code:%d,type:%d",code,type));
    }
}
