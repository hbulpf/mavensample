package dev.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author: RunAtWorld
 * @Date: 2020/5/21 23:50
 */
public class StringUtils {
    public static void printHr(){
        System.out.println("---------------------");
    }
    public static void printHr(String appender, String content) {
        System.out.println("======================" + appender);
        System.out.println(content);
    }

    public static void printHr(String appender) {
        System.out.println("-------------------------- " + appender);
    }

    public static boolean isNull(String str) {
        if(str==null||str.trim().length()<=0){
            return true;
        }
        return false;
    }

    public static String urlEncode(String s, String enc) {
        try {
            return URLEncoder.encode(s, enc);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }
}
