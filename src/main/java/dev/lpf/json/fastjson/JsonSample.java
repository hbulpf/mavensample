package dev.lpf.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import dev.lpf.json.fastjson.entity.Person;
import dev.lpf.json.fastjson.entity.User;
import dev.lpf.json.fastjson.serializable.PersonDTO;
import dev.lpf.json.fastjson.serializable.RequestDTO;

import java.util.Date;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-17 16:07
 **/
public class JsonSample {

    public static void main(String[] args) {
        System.out.println("--------序列化-----------");
        serialize();
        System.out.println("-------反序列化------------");
        deserialize();
        System.out.println("-------进行特定字段定制化------------");
        specificFieldSerialize();
        System.out.println("-------泛型测试------------");
        genericTest();
    }

    public static void serialize() {
        User user = new User();
        user.setId(11L);
        user.setName("西安");
        user.setCreateTime(new Date());
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        //设置json输出格式
        System.out.println("--特定json格式--");
        User user2 = new User();
        user2.setId(11L);
        user2.setCreateTime(new Date());
        String jsonString2 = JSON.toJSONString(user2, SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes);
        System.out.println(jsonString2);
    }

    public static void deserialize() {
        String jsonString = "{\"createTime\":\"2018-08-17 14:38:38\",\"id\":11,\"name\":\"西安\"}";
        User user = JSON.parseObject(jsonString, new TypeReference<User>(){});
        System.out.println(user.getName());
        System.out.println(user.getCreateTime());
    }

    public static void specificFieldSerialize() {
        Person person1 = new Person();
        person1.setName("LiLy");
        person1.setAge(54);
        person1.setSex(true);
        System.out.println("---进行特定字段的定制序列化---");
        String jsonString = JSON.toJSONString(person1);
        System.out.println("person json:  " + jsonString);

        System.out.println("---进行特定字段的定制反序列化---");
        Person person2 = JSON.parseObject(jsonString, Person.class);
        System.out.println(person2);
    }

    public static void genericTest() {
        RequestDTO<PersonDTO> requestDTO = new RequestDTO<PersonDTO>();
        requestDTO.setCaller("callerId");
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(11);
        personDTO.setName("张三");
        requestDTO.setParam(personDTO);

        String jsonString = JSON.toJSONString(requestDTO);
        System.out.println(jsonString);
        //这行是关键代码
        requestDTO = JSON.parseObject(jsonString, new TypeReference<RequestDTO<PersonDTO>>() {
        });

        System.out.println(requestDTO.getParam().getName());
    }
}
