
package dev.db.c3p0.demo;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DBUtilObject Demo
 */
public class DBUtilBoDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger("dev.lpf.db.c3p0.demo.DBUtilBoDemo");


    public static void main(String[] args) {
        test01("ss","sw");
    }

    public static void test01(String username,String pwd) {
        DBUtilObject dbBo = new DBUtilObject();
        dbBo.conn = C3p0Utils.getConnection();// 取用一个连接
        String sql = "select * from `crm-sky-demo`.`t_user` where user_name=? and password=?";
        try {
            // 预处理sql语句
            dbBo.preparedStatement = dbBo.conn.prepareStatement(sql);
            dbBo.preparedStatement.setString(1, username);
            dbBo.preparedStatement.setString(2, pwd);
            // 此时dbBo对象已经封装了一个数据库连接以及要执行的操作
            // 通过数据库操作类来执行这个操作封装类，结果封装回这个操作封装类
            DBUtils.executeQuery(dbBo);
            // 从dbBo类提取操作结果
            if (dbBo.rs.next()) {
                int uid = dbBo.rs.getInt("userid");
                System.out.println(uid);
            }
            // 结果集遍历完了，手动释放连接回连接池
            DBUtils.closeSource(dbBo);
        } catch (SQLException e) {
            LOGGER.error("sql 异常: ", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("其他异常: ", e.getMessage());
            e.printStackTrace();
        }


    }
}
