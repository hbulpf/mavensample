package dev.lpf.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dev.lpf.json.fastjson.entity.Student;
import dev.lpf.json.fastjson.entity.User;
import dev.lpf.json.fastjson.entity.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-17 22:17
 **/
public class QuickStart {

    public static void main(String[] args) {
        System.out.println("--------------------------快速开始--------------------------");
        quickBegin();

        System.out.println("--------------------------对用户/用户组序列化--------------------------");
        usertest();
    }

    public static void quickBegin(){
        List<Student> list=new ArrayList<>();
        Student student=new Student("bob",24);
        Student student12=new Student("lily", 23);
        list.add(student);
        list.add(student12);
        System.out.println("*******javaBean  to jsonString*******");
        String str1=JSON.toJSONString(student);
        System.out.println(str1);
        System.out.println(JSON.toJSONString(list));
        System.out.println();

        System.out.println("******jsonString to javaBean*******");
        //Student stu1=JSON.parseObject(str1,new TypeReference<Student>(){});
        Student stu1=JSON.parseObject(str1,Student.class);
        System.out.println(stu1);
        System.out.println();

        System.out.println("******javaBean to jsonObject******");
        JSONObject jsonObject1=(JSONObject)JSON.toJSON(student);
        System.out.println(jsonObject1.getString("name"));
        System.out.println();

        System.out.println("******jsonObject to javaBean******");
        Student student2=JSON.toJavaObject(jsonObject1, Student.class);
        System.out.println(student2);
        System.out.println();

        System.out.println("*******javaBean to jsonArray******");
        List<Student> stulist=new ArrayList<>();
        for(int i=0;i<5;i++){
            stulist.add(new Student("student"+i, i));

        }
        JSONArray jsonArrays=(JSONArray)JSON.toJSON(stulist);
        for(int i=0;i<jsonArrays.size();i++){
            System.out.println(jsonArrays.getJSONObject(i));
        }
        System.out.println();

        System.out.println("*****jsonArry to javalist******");
        List<Student> myList=new ArrayList<>();
        for(int i=0;i<jsonArrays.size();i++){
            Student student3= JSON.toJavaObject(jsonArrays.getJSONObject(i), Student.class);
            myList.add(student3);
        }
        for(Student stu:myList){
            System.out.println(stu);
        }

        System.out.println();

        System.out.println("*****jsonObject to jsonString*****");
        String str4=JSON.toJSONString(jsonObject1);
        System.out.println(str4);
        System.out.println();

        System.out.println("*******jsonString to jsonObject*****");
        JSONObject jso1=JSON.parseObject(str1);
        System.out.println(jso1.getString("name"));
        System.out.println();

        System.out.println("*****jsonString to jsonArray*****");
        JSONArray jArray=JSON.parseArray(JSON.toJSONString(stulist));
        for(int i=0;i<jArray.size();i++){
            System.out.println(jArray.getJSONObject(i));
        }
        System.out.println();
    }
    public static void usertest() {
        // 构建用户geust
        User guestUser = new User(){
            {
                this.setId(131L);
                this.setAge(18);
                this.setName("guest");
            }
        };
        // 构建用户root
        User rootUser = new User(){
            {
                this.setId(134L);
                this.setAge(28);
                this.setName("root");
            }
        };
        // 构建用户组对象
        System.out.println("--构建用户组 List--");
        UserGroup group = new UserGroup();
        group.setName("admin");
        group.getUserList().add(guestUser);
        group.getUserList().add(rootUser);

        // 用户组对象转JSON串
        String jsonString = JSON.toJSONString(group);
        System.out.println("jsonString:" + jsonString);

        // JSON串转用户组对象
        System.out.println("--JSON串转用户组对象--");
        UserGroup group2 = JSON.parseObject(jsonString, UserGroup.class);
        System.out.println("group2:" + group2);

        // 构建用户对象数组
        System.out.println("--用户对象数组 array--");
        User[] users = new User[2];
        users[0] = guestUser;
        users[1] = rootUser;
        // 用户对象数组转JSON串
        String jsonString2 = JSON.toJSONString(users);
        System.out.println("jsonString2:" + jsonString2);
        // JSON串转用户对象列表
        List<User> userList = JSON.parseArray(jsonString2, User.class);
        System.out.println("userList:" + userList);
    }
}
