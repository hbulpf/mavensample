package dev.lpf.db.c3p0.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * C3P0 Demo
 */
public class C3P0Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger("dev.lpf.db.c3p0.demo.C3p0Utils");

    public static void main(String[] args) {
        insertOrDeleteOrUpdate(
            "INSERT INTO `crm-sky-demo`.`t_user` (user_id,user_name,PASSWORD,age,sex,email,memo,createTime,updateTime) VALUES ('12221','韩信','xinxinsa',22,1,'dss@1ss.om','',NOW(),NOW())");
        select(
            "SELECT user_id,user_name,PASSWORD,age,sex,email,memo,createTime,updateTime FROM `crm-sky-demo`.`t_user` LIMIT 0, 1000;");
    }

    /**
     * 增加，删除，修改  
     *
     * @param sql the sql
     */
    public static void insertOrDeleteOrUpdate(String sql) {
        try {
            Connection connection = C3p0Utils.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            int execute = pst.executeUpdate();
            System.out.println("执行语句：" + sql + "," + execute + "行数据受影响");
            C3p0Utils.close(connection, pst, null);
        } catch (SQLException e) {
            LOGGER.error("异常提醒：" + e.getMessage());
        }
    }

    /**
     * 查询，返回结果集  
     *
     * @param sql the sql
     * @return the list
     */
    public static List<Map<String, Object>> select(String sql) {
        List<Map<String, Object>> returnResultToList = null;
        try {
            Connection connection = C3p0Utils.getConnection();
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            returnResultToList = returnResultToList(resultSet);
            C3p0Utils.close(connection, pst, resultSet);
        } catch (SQLException e) {
            LOGGER.error("异常提醒：" + e);
        }
        return returnResultToList;
    }

    /**
     * 数据返回集合
     *
     * @param resultSet the result set
     * @return the list
     */
    public static List<Map<String, Object>> returnResultToList(ResultSet resultSet) {
        List<Map<String, Object>> values = null;
        try {
            // 键: 存放列的别名, 值: 存放列的值.
            values = new ArrayList<>();
            // 存放字段名
            List<String> columnNames = new ArrayList<>();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                // 字段名
                columnNames.add(rsmd.getColumnLabel(i + 1));
            }

            System.out.println("表字段为：");
            System.out.println(columnNames);
            System.out.println("表数据为：");
            Map<String, Object> map = null;
            // 处理 ResultSet, 使用 while 循环
            while (resultSet.next()) {
                map = new HashMap<>();
                for (String column : columnNames) {
                    Object value = resultSet.getObject(column);
                    map.put(column, value);
                    System.out.print(value + "\t");
                }
                // 把一条记录的 Map 对象放入准备的 List 中
                values.add(map);
                System.out.println();
            }
        } catch (SQLException e) {
            LOGGER.error("异常提醒：" + e);
        }
        return values;
    }
}
