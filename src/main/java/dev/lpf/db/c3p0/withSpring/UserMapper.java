package dev.lpf.db.c3p0.withSpring;

/**
 * Spring User Service
 */
public class SpringUserService {

    private SpringUserDao userDao;

    public void setUserDao(SpringUserDao userDao) {
        this.userDao = userDao;
    }

    public void getAll() {
        System.out.println("service调用dao,查询所有数据...");
        userDao.getAll();
    }

    public void accountTransfer(float money) {
package dev.lpf.db.c3p0.withSpring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.lpf.db.c3p0.beans.User;

/**
 * User Mapper
 *
 */
public class UserMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int index) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("user_name"));
        user.setUserpwd(resultSet.getString("password"));
        return user;
    }
}

        // 小明增加1000
        userDao.increase(1, money);

        // 出现异常：
         int i=10/0;

        // 小李减少1000
        userDao.decrease(2, money);
    }

}
