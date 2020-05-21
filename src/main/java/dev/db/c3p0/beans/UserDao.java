
package dev.db.c3p0.beans;

import java.beans.PropertyVetoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * User Dao
 */
public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            jdbcTemplateDao();
        } catch (PropertyVetoException e) {
            LOGGER.error("jdbcTemplateDao 错误:", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Jdbc template dao.
     * c3p0连接池数据源配置
     * 1.创建dataSources连接池对象
     * 2.初始化连接池的4项基本配置(4项基本配置是所有种类的连接池都必须配置的)
     * a.驱动名称 DriverClass
     * b.数据库地址 JdbcUrl
     * c.用户名 User
     * d.密码 Password
     */
    public static void jdbcTemplateDao() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/mydb");
        dataSource.setUser("root");
        dataSource.setPassword("root1234");

        // 创建模板工具类
        JdbcTemplate jdbcTemplate1 = new JdbcTemplate();
        jdbcTemplate1.setDataSource(dataSource);

        int count = jdbcTemplate1.update("insert into t_user(user_name,password)values(?,?)", "tom", "999");
        if (count > 0) {
            LOGGER.info("成功更新数据 {} 条", count);
        } else {
            LOGGER.warn("更新数据失败");
        }
    }
}
