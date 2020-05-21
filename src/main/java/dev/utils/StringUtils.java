package dev.utils;

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

    public static boolean isNull(String str) {
        if(str==null||str.trim().length()<=0){
            return true;
        }
        return false;
    }
}
