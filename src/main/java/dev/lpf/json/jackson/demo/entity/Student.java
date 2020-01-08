package dev.lpf.json.jackson.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @JsonProperty("stuId")
    private String id;

    @JsonProperty(value = "age",defaultValue = "18")
    @JsonIgnore
    private int age;

    @JsonProperty("friends")
    private Friend[] friends;
}
