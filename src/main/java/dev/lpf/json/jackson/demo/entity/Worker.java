package dev.lpf.json.jackson.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Worker
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("Worker")
public class Worker {
    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;

    @JsonProperty("interests")
    private List<String> interests;
}
