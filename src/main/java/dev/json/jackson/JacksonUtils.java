
package dev.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Json操作工具类
 */
public class JacksonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtils.class);

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 将空对象序列化为空字符串，而不是null
        OBJECT_MAPPER.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        });

        // 反序化时候忽略未知属性
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 序列化时忽略null值
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许使用非双引号属性名字， （这种形式在Javascript中被允许，但是JSON标准说明书中没有）。
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号来包住属性名称和字符串值。
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        // 使用jdk8时间格式
        OBJECT_MAPPER.registerModule(new JavaTimeModule())
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module());
        // 禁止序列化为时间戳
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private JacksonUtils() {
    }

    /**
     * 对象序列化为Json文本
     * 
     * @param obj
     * @return
     */
    public static String object2Json(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonGenerationException e) {
            logger.error("can not encode to json,Generation Error:{}", e);
        } catch (JsonMappingException e) {
            logger.error("can not encode to json,Mapping Error{}", e);
        } catch (IOException e) {
            logger.error("can not encode to json,IO Error:{}", e);
        }
        return null;
    }

    /**
     * Json文本反序列化成对象
     *
     * @param jsonText
     * @param valueType
     * @return
     */
    public static <T> T json2Object(String jsonText, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(jsonText, valueType);
        } catch (JsonParseException e) {
            logger.error("can not decode from json,Parse Error:{}", e);
        } catch (JsonMappingException e) {
            logger.error("can not decode from json,Mapping Error:{}", e);
        } catch (IOException e) {
            logger.error("can not decode from json,IO Error:{}", e);
        }
        return null;
    }

    /**
     * Json文本反序列化成对象，Map,Set,List等复杂类型
     * 
     * @param jsonText
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String jsonText, TypeReference<T> typeReference) {
        if (isEmptyStr(jsonText)) {
            return null;
        }
        try {
            return (T) OBJECT_MAPPER.readValue(jsonText, typeReference);
        } catch (JsonParseException e) {
            logger.error("can not decode from json,Parse Error:{}", e);
        } catch (JsonMappingException e) {
            logger.error("can not decode from json,Mapping Error:{}", e);
        } catch (IOException e) {
            logger.error("can not decode from json,IO Error:{}", e);
        }
        return null;
    }

    /**
     * 读取Json文本中，成为 ObjectNode
     * 
     * @param jsonText
     * @return
     */
    public static JsonNode toJsonNode(String jsonText) {
        if (isEmptyStr(jsonText)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readTree(jsonText);
        } catch (JsonMappingException e) {
            logger.error("can not decode from json,Mapping Error:{}", e);
        } catch (JsonProcessingException e) {
            logger.error("can not decode from json,Processing Error:{}", e);
        }
        return null;
    }

    /**
     * 从Json数组文本中读取，成为 ArrayNode
     * 如 [{"a":12,"b",134}]
     * 
     * @param jsonText
     * @return
     */
    public static ArrayNode toJsonArrayNode(String jsonText) {
        if (isEmptyStr(jsonText)) {
            return null;
        }
        try {
            JsonNode node = OBJECT_MAPPER.readTree(jsonText);
            if (!node.isArray()) {
                throw new IllegalArgumentException("" + jsonText + ", is not valid json array string");
            }
            return (ArrayNode) node;
        } catch (JsonMappingException e) {
            logger.error("can not decode from json,Mapping Error:{}", e);
        } catch (JsonProcessingException e) {
            logger.error("can not decode from json,Processing Error:{}", e);
        }
        return null;
    }

    /**
     * 读取Json文本中，成为 ObjectNode
     * 
     * @param jsonText
     * @return
     */
    public static ObjectNode toObjectNode(String jsonText) {
        if (isEmptyStr(jsonText)) {
            return null;
        }
        try {
            JsonNode node = OBJECT_MAPPER.readTree(jsonText);
            if (!node.isObject()) {
                throw new IllegalArgumentException("" + jsonText + ", is not valid json string");
            }
            return (ObjectNode) node;
        } catch (JsonMappingException e) {
            logger.error("can not decode from json,Mapping Error:{}", e);
        } catch (JsonProcessingException e) {
            logger.error("can not decode from json,Processing Error:{}", e);
        }
        return null;
    }

    /**
     * 将一个JsonNode节点转成一个带泛型的Java Object
     *
     * @param node
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T node2Object(JsonNode node, TypeReference<T> typeReference) {
        if (node == null) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(node, typeReference);
    }

    /**
     * 取JsonNode节点的值为Text
     * 如：
     * {
     * "code": "0",
     * "data": {
     * "type": 1,
     * "msg": {
     * "content": "ok"
     * }
     * }
     * }
     * data节点 的 text 为空，content节点 的 text 为 ok, context节点 的toString为 "ok"
     * 
     * @param node
     * @param key
     * @return
     */
    public static String getValueAsText(JsonNode node, String key) {
        if (node == null || node.get(key) == null) {
            return null;
        }
        return node.path(key).asText();
    }

    /**
     * 取JsonNode节点的值为数值
     * 
     * @param node
     * @param key
     * @return
     */
    public static Integer getValueAsInt(JsonNode node, String key) {
        if (node == null || node.get(key) == null) {
            return null;
        }

        if (node.get(key).isIntegralNumber() || node.get(key).isInt()) {
            return node.path(key).asInt();
        }
        return null;
    }

    /**
     * 取JsonNode节点的值为数值,如果转换不成功，返回默认值
     * 
     * @param node
     * @param key
     * @param defValue
     * @return
     */
    public static Integer getValueAsInt(JsonNode node, String key, Integer defValue) {
        if (node == null || node.get(key) == null) {
            return null;
        }
        if (node.get(key).isIntegralNumber() || node.get(key).isInt()) {
            return node.path(key).asInt(defValue);
        }
        return null;
    }

    /**
     * 取 jsonText 文本的 xpath 处的JsonNode
     * 如：
     * {
     * "code": "0",
     * "data": {
     * "type": 1,
     * "msg": {
     * "content": "ok"
     * }
     * }
     * }
     * JsonNode content = mapper.readTree(json).at("/code/type/content");
     *
     * @param jsonText
     * @param xpath
     * @return
     */
    public static JsonNode readXpath(String jsonText, String xpath) {
        if (isEmptyStr(jsonText)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readTree(jsonText).at(xpath);
        } catch (JsonMappingException e) {
            logger.error("can not decode from json,Mapping Error:{}", e);
        } catch (JsonProcessingException e) {
            logger.error("can not decode from json,Processing Error:{}", e);
        }
        return null;
    }

    /**
     * 将 ArrayNode 的子对象数组转换为List
     * 
     * @param arrayNode
     * @return
     */
    public static List<JsonNode> toNodeList(ArrayNode arrayNode) {
        if (arrayNode == null) {
            return Collections.emptyList();
        }
        List<JsonNode> list = new ArrayList();
        arrayNode.forEach(n -> list.add(n));
        return list;
    }

    /**
     * 将 JsonNode 的子元素依次转换为List
     * 
     * @param node
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> List<T> toObjectList(JsonNode node, Class<T> valueType) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList();
        node.forEach(n -> list.add(OBJECT_MAPPER.convertValue(n, valueType)));
        return list;
    }

    /**
     * 将 JsonNode 的子元素依次转换为List
     * 
     * @param node
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> List<T> toObjectList(JsonNode node, TypeReference<T> typeReference) {
        if (node == null) {
            return Collections.emptyList();
        }
        List<T> list = new ArrayList();
        node.forEach(n -> list.add(OBJECT_MAPPER.convertValue(n, typeReference)));
        return list;
    }

    /**
     * 将 jsonText 的 xpath 层级下的子元素依次转换为List
     * 
     * @param jsonText
     * @param xpath
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> List<T> toObjectList(String jsonText, String xpath, Class<T> valueType) {
        JsonNode node = readXpath(jsonText, xpath);
        return toObjectList(node, valueType);
    }

    /**
     * 判断为非空字符串
     * 
     * @param jsonText
     * @return
     */
    private static boolean isEmptyStr(String jsonText) {
        if (jsonText == null || "".equals(jsonText.trim())) {
            return true;
        }
        return false;
    }

}
