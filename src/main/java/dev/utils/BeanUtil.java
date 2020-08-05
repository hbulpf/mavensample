package dev.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Bean 工具类
 */
public class BeanUtil {
    /**
     * Bean 复制属性值
     *
     * @param source 源对象：属性值的来源
     * @param target 目标对象：被赋值的对象
     * @throws ReflectiveOperationException 反射异常
     */
    public static void copyBeanProperties(Object source, Object target) throws ReflectiveOperationException {
        BeanUtils.copyProperties(source, target);
        Class classtyType = source.getClass();
        Field[] fields = classtyType.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(Date.class)) {
                field.setAccessible(true);
                Object value = field.get(source);
                if (value != null) {
                    String str = ((Date) value).toString();
                    Field f = target.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    f.set(target, str);
                }
            }
        }
    }
}
