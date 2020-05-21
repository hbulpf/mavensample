
package dev.db.c3p0.withSpring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 使用c3p0连接池结合Spring容器
 */
public class SpringC3P0Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger("dev.lpf.db.c3p0.withSpring.SpringC3P0Demo");

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
//        System.out.println("=========c3p0Jdbctemplate1=========");
//        c3p0Jdbctemplate1();
//        System.out.println("=========c3p0Jdbctemplate2=========");
//        c3p0Jdbctemplate2();
        System.out.println("=========springTXAop=========");
        springTXAop();
    }

    /**
     * c3p0 jdbctemplate 1.
     */
    public static void c3p0Jdbctemplate1() {
        String xmlpath = "c3p0/c3p0_springconfig_1.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlpath);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbctemplate");
        int count = jdbcTemplate.update("insert into t_user(user_name,password)values(?,?)", "tiger", "45611564165");
        if (count > 0) {
            LOGGER.info("成功更新数据 {} 条", count);
        } else {
            LOGGER.warn("更新数据失败");
        }
    }

    /**
     * c3p0 jdbctemplate 2.
     */
    public static void c3p0Jdbctemplate2() {
        String xmlpath = "c3p0/c3p0_springconfig_2.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlpath);
        System.out.println("=========获取所有数据=========");
        SpringUserDao dao = (SpringUserDao) context.getBean("c3p0UserDao");
        System.out.println(dao.getAll());
        System.out.println("=========获取 1 条数据=========");
        System.out.println(dao.getbyId(12221));
    }

    /**
     * Spring 声明式事务管理
     */
    public static void springTXAop() {
        String xmlpath = "c3p0/c3p0_springconfig_3.xml";
        ApplicationContext context = new ClassPathXmlApplicationContext(xmlpath);
        SpringUserService userService = (SpringUserService) context.getBean("userService");
        userService.accountTransfer(10.3f);
    }

}
