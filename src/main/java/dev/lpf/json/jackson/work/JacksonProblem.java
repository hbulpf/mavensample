
package dev.json.jackson.crm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import dev.utils.StringUtils;

/**
 * Jackson 问题处理
 */
public class JacksonProblem {
    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonProblem.class);

    static String str1 = "{\n"
        + "    \"error_code\": \"CBC.0000\",\n"
        + "    \"error_msg\": \"success.\",\n"
        + "    \"message\": \"success.\",\n"
        + "    \"cbList\": [\n"
        + "        {\n"
        + "            \"Number\": \"180D\",\n"
        + "            \"Name\": \"测试1\",\n"
        + "            \"id\": \"232082\",\n"
        + "            \"status\": \"Start\",\n"
        + "            \"type\": \"New\",\n"
        + "            \"beginTime\": \"2020-05-19\",\n"
        + "            \"expireTime\": \"2020-06-18\"\n"
        + "        },\n"
        + "        {\n"
        + "            \"Number\": \"182D\",\n"
        + "            \"Name\": \"测试2\",\n"
        + "            \"id\": \"23203382\",\n"
        + "            \"status\": \"Stop\",\n"
        + "            \"type\": \"New\",\n"
        + "            \"beginTime\": \"2020-05-19\",\n"
        + "            \"expireTime\": \"2020-06-18\"\n"
        + "        }\n"
        + "    ],\n"
        + "    \"isSuccess\": true\n"
        + "}";

    static String str2 = "{\n"
        + "    \"error_code\": \"CBC.0000\",\n"
        + "    \"error_msg\": \"success.\",\n"
        + "    \"message\": \"success.\",\n"
        + "    \"cbList\": [\n"
        + "        {\n"
        + "            \"Number\": \"1805\",\n"
        + "            \"Name\": \"测试4\",\n"
        + "            \"id\": \"811\",\n"
        + "            \"status\": \"Start\",\n"
        + "            \"type\": \"New\",\n"
        + "            \"beginTime\": \"2020-05-19\",\n"
        + "            \"expireTime\": \"2020-06-18\"\n"
        + "        },\n"
        + "        {\n"
        + "            \"Number\": \"1829\",\n"
        + "            \"Name\": \"测试3\",\n"
        + "            \"id\": \"4585\",\n"
        + "            \"status\": \"Stop\",\n"
        + "            \"type\": \"New\",\n"
        + "            \"beginTime\": \"2020-05-19\",\n"
        + "            \"expireTime\": \"2020-06-18\"\n"
        + "        }\n"
        + "    ],\n"
        + "    \"isSuccess\": true\n"
        + "}";

    static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 将空对象序列化为空字符串，而不是null
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
                // gen.writeNull();
            }
        });

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static void main(String[] args) {
        test1();
        try {
            test2();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void test1() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootOne = null;
        JsonNode rootTwo = null;
        try {
            rootOne = mapper.readTree(str1);
            rootTwo = mapper.readTree(str2);

        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        if (rootOne.get("infoList") != null && !rootOne.get("infoList").asText().equals("null")) {
            JsonNode cbNodeOne = rootOne.get("infoList");
            ArrayNode arrayNode1 = null;
            ArrayNode arrayNode2 = null;
            if (rootTwo.get("infoList") != null && !rootTwo.get("infoList").asText().equals("null")) {
                JsonNode cbNodeTwo = rootTwo.get("infoList");
                if (cbNodeTwo.isArray()) {
                    arrayNode2 = (ArrayNode) cbNodeTwo;
                }
            }
            if (cbNodeOne.isArray() && arrayNode2 != null) {
                arrayNode1 = (ArrayNode) cbNodeOne;
                arrayNode1.addAll(arrayNode2);
            }
            if (arrayNode1.isEmpty()) {
                ((ObjectNode) rootOne).put("isSuccess", false);
            } else {
                ((ObjectNode) rootOne).put("isSuccess", true);
            }
        }
        System.out.println(rootOne);
    }

    public static void test2() throws JsonProcessingException {
        String str1 =
            "{\n"
                + "            \"Number\": \"1805\",\n"
                + "            \"Name\": \"测试4\",\n"
                + "            \"id\": \"811\",\n"
                + "            \"status\": \"Start\",\n"
                + "            \"type\": \"New\",\n"
                + "            \"beginTime\": \"2020-05-19\",\n"
                + "            \"expireTime\": \"2020-06-18\"\n"
                + "        }";
        String str2 =
            "        {\n"
                + "            \"Number\": \"1829\",\n"
                + "            \"Name\": \"测试3\",\n"
                + "            \"id\": \"4585\",\n"
                + "            \"status\": \"Stop\",\n"
                + "            \"type\": \"New\",\n"
                + "            \"beginTime\": \"2020-05-19\",\n"
                + "            \"expireTime\": \"2020-06-18\"\n"
                + "        }";
        WorkflowObj wf1 = objectMapper.readValue(str1, WorkflowObj.class);
        WorkflowObj wf2 = objectMapper.readValue(str2, WorkflowObj.class);
        List<WorkflowObj> list1 = new ArrayList<>();
        list1.add(null);
        List<WorkflowObj> list2 = new ArrayList<>();
        list2.add(wf1);
        ResponseEntity<WorkflowObj> r1 = new ResponseEntity();
        r1.setInfoList(list1);
        ResponseEntity<WorkflowObj> r2 = new ResponseEntity();
        r2.setInfoList(list2);

        Response rsp1 = Response.status(200).entity(r1).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
        Response rsp2 = Response.status(200).entity(r2).header(HttpHeaders.CONTENT_TYPE, "application/json").build();
        Response rsp3 = combineListResponse2(rsp1, rsp2);
        StringUtils.printHr();
        System.out.println("rsp1\n" + objectMapper.writeValueAsString(rsp1.getEntity()));
        StringUtils.printHr();
        System.out.println("rsp2\n" + objectMapper.writeValueAsString(rsp2.getEntity()));
        StringUtils.printHr();
        System.out.println("rsp3\n" + objectMapper.writeValueAsString(rsp3.getEntity()));
    }

    public static Response combineListResponse2(Response rspOne, Response rspTwo) throws JsonProcessingException {
        if (rspOne == null || rspOne.getEntity() == null) {
            return rspTwo;
        }
        if (rspTwo == null || rspTwo.getEntity() == null) {
            return rspOne;
        }

        JsonNode rootOne = null;
        JsonNode rootTwo = null;

        try {
            rootOne = objectMapper.readTree(objectMapper.writeValueAsString(rspOne.getEntity()));
            rootTwo = objectMapper.readTree(objectMapper.writeValueAsString(rspTwo.getEntity()));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        String findNode = "infoList";
        ArrayNode arrayNode1 = null;
        ArrayNode arrayNode2 = null;
        if (rootOne.get(findNode) != null) {
            arrayNode1 = (ArrayNode) rootOne.get(findNode);
        }
        if (rootTwo.get(findNode) != null) {
            arrayNode2 = (ArrayNode) rootTwo.get(findNode);
        }
        if (arrayNode1 == null && arrayNode2 == null) {
            return rspOne;
        }
        ArrayNode combinedNode = arrayNode1 != null ? arrayNode1.deepCopy() : arrayNode2.deepCopy();
        combinedNode.removeAll();
        if (arrayNode1 != null && !StringUtils.isNull(arrayNode1.asText())) {
            System.out.println(arrayNode1);
            combinedNode.addAll(arrayNode1);
        }
        if (arrayNode2 != null && !StringUtils.isNull(arrayNode2.toString())) {
            combinedNode.addAll(arrayNode2);
        }
        if (combinedNode == null || combinedNode.isEmpty()) {
            ((ObjectNode) rootOne).put("isSuccess", false);
        } else {
            ((ObjectNode) rootOne).put("isSuccess", true);
        }
        StringUtils.printHr("combinedNode", combinedNode.toString());
        // ((ObjectNode) resNode).remove(findNode);
        // ((ObjectNode) resNode).set(findNode,combinedNode);
        ((ObjectNode) rootOne).replace(findNode, combinedNode);
        StringUtils.printHr("res", rootOne.toString());
        return Response.status(rspOne.getStatus())
            .entity(rootOne)
            .header(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8")
            .build();
    }
}
