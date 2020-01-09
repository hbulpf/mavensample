# FastJSON 用法

> parseXXX 从 json字符串到json对象或Java对象  
> toJSONString() 从 json对象或Java对象到json 字符串

![](fastjson.png)

##### 常用方法

1.Fastjson中的经常调用的方法:

```java
public static final Object parse(String text); 　　// 把JSON文本parse为JSONObject或者JSONArray 

public static final JSONObject parseObject(String text)；　　 // 把JSON文本parse成JSONObject 

public static final T parseObject(String text, Class clazz); 　　// 把JSON文本parse为JavaBean 

public static final JSONArray parseArray(String text); 　　// 把JSON文本parse成JSONArray 

public static final List parseArray(String text, Class clazz); 　　//把JSON文本parse成JavaBean集合 

public static final String toJSONString(Object object); 　　// 将JavaBean序列化为JSON文本 

public static final String toJSONString(Object object, boolean prettyFormat); 　　// 将JavaBean序列化为带格式的JSON文本 

public static final Object toJSON(Object javaObject); 　　//将JavaBean转换为JSONObject或者JSONArray。
```

2.Fastjson字符串转List<Map<String,Object>>(), 或者List<String>()的用法;

```java
List<Map<String, Object>> list = JSONObject.parseObject(respJson, new TypeReference<List<Map<String, Object>>>() {});
```

###### SerializerFeature常用属性
```java
public static String toJSONString(Object object, SerializerFeature... features)
```

例如：`JSON.toJSONString(resultMap, SerializerFeature.WiteMapNullValue);`


```java
QuoteFieldNames---输出key时是否使用双引号,默认为true
UseSingleQuotes---使用单引号而不是双引号,默认为false
UseISO8601DateFormat---Date使用ISO8601格式输出，默认为false
WriteMapNullValue---是否输出值为null的字段,默认为false
WriteEnumUsingToString---Enum输出name()或者original,默认为false
WriteNullListAsEmpty---List字段如果为null,输出为[],而非null
WriteNullStringAsEmpty---字符类型字段如果为null,输出为”“,而非null
WriteNullNumberAsZero---数值字段如果为null,输出为0,而非null
WriteNullBooleanAsFalse---Boolean字段如果为null,输出为false,而非null
SkipTransientField---如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
SortField---按字段名称排序后输出。默认为false
WriteTabAsSpecial---把\t做转义输出，默认为false不推荐设为true
PrettyFormat---结果是否格式化,默认为false
WriteClassName---序列化时写入类型信息，默认为false。反序列化是需用到
DisableCircularReferenceDetect---消除对同一对象循环引用的问题，默认为false
WriteSlashAsSpecial---对斜杠’/’进行转义
BrowserCompatible---将中文都会序列化为\uXXXX格式，字节数会多一些，但是能兼容IE 6，默认为false
WriteDateUseDateFormat---全局修改日期格式,默认为false。
DisableCheckSpecialChar---一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
BeanToArray---将对象转为array输出	
```

## FastJSON 一般用法
### Demo1 - 简单使用

Student.java
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private int age;
}
```

测试使用
```java
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
```

结果

```java
*******javaBean  to jsonString*******
{"age":24,"name":"bob"}
[{"age":24,"name":"bob"},{"age":23,"name":"lily"}]

******jsonString to javaBean*******
Student(name=bob, age=24)

******javaBean to jsonObject******
bob

******jsonObject to javaBean******
Student(name=bob, age=24)

*******javaBean to jsonArray******
{"name":"student0","age":0}
{"name":"student1","age":1}
{"name":"student2","age":2}
{"name":"student3","age":3}
{"name":"student4","age":4}

*****jsonArry to javalist******
Student(name=student0, age=0)
Student(name=student1, age=1)
Student(name=student2, age=2)
Student(name=student3, age=3)
Student(name=student4, age=4)

*****jsonObject to jsonString*****
{"name":"bob","age":24}

*******jsonString to jsonObject*****
bob

*****jsonString to jsonArray*****
{"name":"student0","age":0}
{"name":"student1","age":1}
{"name":"student2","age":2}
{"name":"student3","age":3}
{"name":"student4","age":4}
```

### Demo2 - 复杂对象
User.java

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String name;

    private int age;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date   createTime;

}
```

UserGroup.java
```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGroup {
    private String name;
    List<User> userList = new ArrayList<>();
}
```

使用

```java
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
```

结果
```java
--构建用户组 List--
jsonString:{"name":"admin","userList":[{"age":18,"id":131,"name":"guest"},{"age":28,"id":134,"name":"root"}]}
--JSON串转用户组对象--
group2:UserGroup(name=admin, userList=[User(id=131, name=guest, age=18, createTime=null), User(id=134, name=root, age=28, createTime=null)])
--用户对象数组 array--
jsonString2:[{"age":18,"id":131,"name":"guest"},{"age":28,"id":134,"name":"root"}]
userList:[User(id=131, name=guest, age=18, createTime=null), User(id=134, name=root, age=28, createTime=null)]
```

## JSONField与JSONType注解的使用
#### JSONField

fastjson提供了JSONField对序列化与反序列化进行定制，比如可以指定字段的名称，序列化的顺序。JSONField用于属性，方法方法参数上。

JSONField的源码如下：
```java
package com.alibaba.fastjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface JSONField {
// 配置序列化和反序列化的顺序
    int ordinal() default 0;
// 指定字段的名称
    String name() default "";
// 指定字段的格式，对日期格式有用
    String format() default "";
 // 是否序列化
    boolean serialize() default true;
// 是否反序列化
    boolean deserialize() default true;
//字段级别的SerializerFeature
    SerializerFeature[] serialzeFeatures() default {};
    Feature[] parseFeatures() default {};
   //给属性打上标签， 相当于给属性进行了分组
    String label() default "";
    
    boolean jsonDirect() default false;
    
//制定属性的序列化类
    Class<?> serializeUsing() default Void.class;
 //制定属性的反序列化类
    Class<?> deserializeUsing() default Void.class;

    String[] alternateNames() default {};

    boolean unwrapped() default false;
} 
```

其中serializeUsing与deserializeUsing可以用于对字段的序列化与反序列化进行定制化。比如我们在User实体上加上个sex属性，类型为boolean。下面分别定义了序列化类与反序列化类，

序列化类代码如下：
```java
package com.ivan.json.converter;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

public class SexSerializer implements ObjectSerializer {

    public void write(JSONSerializer serializer,
                      Object object,
                      Object fieldName,
                      Type fieldType,
                      int features)
            throws IOException {
        Boolean value = (Boolean) object;
        String text = "女";
        if (value != null && value == true) {
            text = "男";
        }
        serializer.write(text);
    }

} 
```

反序列化类代码如下：

```java
package com.ivan.json.converter;

import java.lang.reflect.Type;


import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;

public class SexDeserialize implements ObjectDeserializer {

    public <T> T deserialze(DefaultJSONParser parser,
                                        Type type,
                                        Object fieldName) {
        


        String sex = parser.parseObject(String.class);
        if ("男".equals(sex)) {
            return (T) Boolean.TRUE;
        } else {
            return (T) Boolean.FALSE;
        }
    }

    public int getFastMatchToken() {
        return JSONToken.UNDEFINED;
    }

}
```

#### JSONType

fastjosn提供了JSONType用于类级别的定制化.

JSONType的源码如下：

```java
package com.alibaba.fastjson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Retention(RetentionPolicy.RUNTIME)
//需要标注在类上
@Target({ ElementType.TYPE })
public @interface JSONType {

    boolean asm() default true;
//这里可以定义输出json的字段顺序
    String[] orders() default {};
//包含的字段
    String[] includes() default {};
//不包含的字段
    String[] ignores() default {};
//类级别的序列化特性定义
    SerializerFeature[] serialzeFeatures() default {};
    Feature[] parseFeatures() default {};
    //按字母顺序进行输出
    boolean alphabetic() default true;
    
    Class<?> mappingTo() default Void.class;
    
    Class<?> builder() default Void.class;
    
    String typeName() default "";

    String typeKey() default "";
    
    Class<?>[] seeAlso() default{};
    //序列化类
    Class<?> serializer() default Void.class;
    //反序列化类
    Class<?> deserializer() default Void.class;

    boolean serializeEnumAsJavaBean() default false;

    PropertyNamingStrategy naming() default PropertyNamingStrategy.CamelCase;

    Class<? extends SerializeFilter>[] serialzeFilters() default {};
}
```

### SerializeFilter

fastjson通过SerializeFilter编程扩展的方式定制序列化fastjson支持以下SerializeFilter用于不同常景的定制序列化：

- PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
- PropertyPreFilter根据PropertyName判断是否序列化
- NameFilter 序列化时修改Key
- ValueFilter 序列化时修改Value
- BeforeFilter 在序列化对象的所有属性之前执行某些操作
- AfterFilter 在序列化对象的所有属性之后执行某些操作
- LabelFilter根据 JsonField配置的label来判断是否进行输出

```java
package com.alibaba.fastjson.serializer;

/**
 * @author wenshao[szujobs@hotmail.com]
 */
public interface PropertyFilter extends SerializeFilter {

    /**
     * @param object the owner of the property
     * @param name the name of the property
     * @param value the value of the property
     * @return true if the property will be included, false if to be filtered out
    * 根据 属性的name与value判断是否进行序列化
     */
    boolean apply(Object object, String name, Object value);
}
```

```java
package com.alibaba.fastjson.serializer;

public interface PropertyPreFilter extends SerializeFilter {

//根据 object与name判断是否进行序列化
    boolean apply(JSONSerializer serializer, Object object, String name);
}
```

```java
package com.alibaba.fastjson.serializer;

public interface NameFilter extends SerializeFilter {
//根据 name与value的值，返回json字段key的值
    String process(Object object, String name, Object value);
}
```

```java
package com.alibaba.fastjson.serializer;

public interface ValueFilter extends SerializeFilter {
  //根据name与value定制输出json的value
    Object process(Object object, String name, Object value);
}
```

```java
package com.alibaba.fastjson.serializer;

public abstract class BeforeFilter implements SerializeFilter {

    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal<JSONSerializer>();
    private static final ThreadLocal<Character>      seperatorLocal  = new ThreadLocal<Character>();

    private final static Character                   COMMA           = Character.valueOf(',');

    final char writeBefore(JSONSerializer serializer, Object object, char seperator) {
        serializerLocal.set(serializer);
        seperatorLocal.set(seperator);
        writeBefore(object);
        serializerLocal.set(null);
        return seperatorLocal.get();
    }

    protected final void writeKeyValue(String key, Object value) {
        JSONSerializer serializer = serializerLocal.get();
        char seperator = seperatorLocal.get();
        serializer.writeKeyValue(seperator, key, value);
        if (seperator != ',') {
            seperatorLocal.set(COMMA);
        }
    }
//需要实现的方法，在实际实现中可以调用writeKeyValue增加json的内容
    public abstract void writeBefore(Object object);
}
```


```java
package com.alibaba.fastjson.serializer;

/**
 * @since 1.1.35
 */
public abstract class AfterFilter implements SerializeFilter {

    private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal<JSONSerializer>();
    private static final ThreadLocal<Character>      seperatorLocal  = new ThreadLocal<Character>();

    private final static Character                   COMMA           = Character.valueOf(',');

    final char writeAfter(JSONSerializer serializer, Object object, char seperator) {
        serializerLocal.set(serializer);
        seperatorLocal.set(seperator);
        writeAfter(object);
        serializerLocal.set(null);
        return seperatorLocal.get();
    }

    protected final void writeKeyValue(String key, Object value) {
        JSONSerializer serializer = serializerLocal.get();
        char seperator = seperatorLocal.get();
        serializer.writeKeyValue(seperator, key, value);
        if (seperator != ',') {
            seperatorLocal.set(COMMA);
        }
    }
//子类需要实现的方法，实际使用的时候可以调用writeKeyValue增加内容
    public abstract void writeAfter(Object object);
}
```

```java
package com.alibaba.fastjson.serializer;

//根据 JsonField配置的label来判断是否进行输出
public interface LabelFilter extends SerializeFilter {
    boolean apply(String label);
}
```

## 泛型反序列化

fastjson通过TypeReference来实现泛型的反序列化，以下是一个简单的例子程序。

首先定义了BaseDTO用于所有DTO的父类，代码如下：

```java
package com.ivan.frame.dto.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class BaseDTO implements Serializable{

    private static final long  serialVersionUID = 2230553030766621644L;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
```

RequestDTO用于抽像所有的请求DTO，里面有个泛型参数.代码如下：
```java
package com.ivan.frame.dto.common;


public final class RequestDTO<T extends BaseDTO> extends BaseDTO {

    private static final long serialVersionUID = -2780042604928728379L;

    /**
     * 调用方的名称
     */
    private String            caller;

    /**
     * 请求参数
     */
    private T                 param;
    

 
    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    /**
     * 获取请求参数
     */
    public T getParam() {
        return param;
    }

    /**
     * 设置请求参数
     * 
     * @param param 请求参数
     */
    public void setParam(T param) {
        this.param = param;
    }

}
```

定义一个具体的业务对象， PersonDTO代码如下：
```java
package com.ivan.frame.dto;

import com.ivan.frame.dto.common.BaseDTO;

public class PersonDTO extends BaseDTO {
    
    private static final long serialVersionUID = 4637634512292751986L;
    
    private int id;
    private int age;
    private String name;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
```

通过JSON.parseObject传入TypeReference对象进行泛型转换，代码如下：
```java
package com.ivan.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ivan.frame.dto.PersonDTO;
import com.ivan.frame.dto.common.RequestDTO;

public class GenericTest {

    public static void main(String[] args) {
        RequestDTO<PersonDTO> requestDTO = new RequestDTO<PersonDTO>();
        requestDTO.setCaller("callerId");
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAge(11);
        personDTO.setName("张三");
        requestDTO.setParam(personDTO);
        
        String jsonString = JSON.toJSONString(requestDTO);
        System.out.println(jsonString);
        //这行是关键代码
        requestDTO = JSON.parseObject(jsonString, new TypeReference<RequestDTO<PersonDTO>>(){});
        
        
        System.out.println(requestDTO.getParam().getName());
    }
}
```


## fastjson各种概念

JSON：本身是Abstract，提供了一系统的工具方法方便用户使用的API。
#### 序列化相关的概念

- SerializeConfig：内部是个map容器主要功能是配置并记录每种Java类型对应的序列化类。
- SerializeWriter 继承自Java的Writer，其实就是个转为FastJSON而生的StringBuilder，完成高性能的字符串拼接。
- SerializeFilter: 用于对对象的序列化实现各种定制化的需求。
- SerializerFeature：对于对输出的json做各种格式化的需求。
- JSONSerializer：相当于一个序列化组合器，集成了SerializeConfig， SerializeWriter ， SerializeFilter与SerializerFeature。

序列化的入口代码如下，上面提到的各种概念都包含了
```java
    public static byte[] toJSONBytes(Charset charset, //
                                     Object object, //
                                     SerializeConfig config, //
                                     SerializeFilter[] filters, //
                                     String dateFormat, //
                                     int defaultFeatures, //
                                     SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);

        try {
            JSONSerializer serializer = new JSONSerializer(out, config);

            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setDateFormat(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }

            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    serializer.addFilter(filter);
                }
            }

            serializer.write(object);
            return out.toBytes(charset);
        } finally {
            out.close();
        }
    }
```

#### 反序列化相关的概念

- ParserConfig：内部通过一个map保存各种ObjectDeserializer。
- JSONLexer : 与SerializeWriter相对应，用于解析json字符串。
- JSONToken：定义了一系统的特殊字符，这些称为token。
- ParseProcess ：定制反序列化，类似于SerializeFilter。
- Feature：用于定制各种反序列化的特性。
- DefaultJSONParser：相当于反序列化组合器，集成了ParserConfig，Feature， JSONLexer 与ParseProcess。

反序列化的入口代码如下，上面的概念基本都包含了：

```java
    @SuppressWarnings("unchecked")
    public static <T> T parseObject(String input, Type clazz, ParserConfig config, ParseProcess processor,
                                          int featureValues, Feature... features) {
        if (input == null) {
            return null;
        }

        if (features != null) {
            for (Feature feature : features) {
                featureValues |= feature.mask;
            }
        }

        DefaultJSONParser parser = new DefaultJSONParser(input, config, featureValues);

        if (processor != null) {
            if (processor instanceof ExtraTypeProvider) {
                parser.getExtraTypeProviders().add((ExtraTypeProvider) processor);
            }

            if (processor instanceof ExtraProcessor) {
                parser.getExtraProcessors().add((ExtraProcessor) processor);
            }

            if (processor instanceof FieldTypeResolver) {
                parser.setFieldTypeResolver((FieldTypeResolver) processor);
            }
        }

        T value = (T) parser.parseObject(clazz, null);

        parser.handleResovleTask(value);

        parser.close();

        return (T) value;
    }
```

## 与Spring MVC整合

 fastjson提供了FastJsonHttpMessageConverter用于将Spring mvc里的body数据(必须是json格式)转成Controller里的请求参数或者将输出的对象转成json格式的数据。spring mvc里的核心配置如下：

```java
    <mvc:annotation-driven conversion-service="conversionService">
        <mvc:message-converters register-defaults="true">
            <bean
                class="com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter">
                <property name="supportedMediaTypes">
                    <list>
                        <value>text/html;charset=UTF-8</value>
                        <value>application/json;charset=UTF-8</value>
                    </list>
                </property>
                <property name="features">
                    <array>
                        <value>WriteMapNullValue</value>
                        <value>WriteNullStringAsEmpty</value>
                    </array>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```

这里有一个注意点，当你用Spring 3或者fastjson使用的是1.1.x的版本，在转换带有泛型参数类型的时候无法进行转换，而在Spring4配合fastjson1.2.X的版本可以解决这个问题。FastJsonHttpMessageConverter read的核心代码如下：

```java
public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object>//
        implements GenericHttpMessageConverter<Object> {

//将json转成javabean的时候会调用。这里的type
    public Object read(Type type, //
                       Class<?> contextClass, //
                       HttpInputMessage inputMessage //
    ) throws IOException, HttpMessageNotReadableException {
        return readType(getType(type, contextClass), inputMessage);
    }

//这里会通过Spring4TypeResolvableHelper得到类型参数，
    protected Type getType(Type type, Class<?> contextClass) {
        if (Spring4TypeResolvableHelper.isSupport()) {
            return Spring4TypeResolvableHelper.getType(type, contextClass);
        }

        return type;
    }

}
```

# 参考
1. [fastjson 官方 wiki](https://github.com/alibaba/fastjson/wiki)
1. [fastjson Samples DataBind](https://github.com/alibaba/fastjson/wiki/Samples-DataBind)
1. [fastjson 简明教程](https://www.w3cschool.cn/fastjson/)    
1. [fastjson详解](https://www.jianshu.com/p/eaeaa5dce258)

