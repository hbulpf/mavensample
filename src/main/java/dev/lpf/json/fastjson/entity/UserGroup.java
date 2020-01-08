package dev.lpf.json.fastjson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gradle-demo-1
 * @description:
 * @author: Li Pengfei
 * @create: 2019-12-17 20:13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    private String name;
    List<User> userList = new ArrayList<>();
}
