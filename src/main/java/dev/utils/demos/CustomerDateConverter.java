package dev.utils.demos;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomerDateConverter implements Converter {
    //根据传来的时间字符串格式：例如：20130224201210
    private final static SimpleDateFormat DATE_FORMATE_SHOW = new SimpleDateFormat("yyyyMMddHHmmss");

    public Object convert(Class type, Object value) {
        if (type.equals(java.util.Date.class)) {
            try {
                return DATE_FORMATE_SHOW.parse(value.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}