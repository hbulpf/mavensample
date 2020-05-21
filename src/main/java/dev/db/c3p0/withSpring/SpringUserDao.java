
package dev.db.c3p0.withSpring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dev.db.c3p0.beans.User;

/**
 * Spring UserDao
 */
public class SpringUserDao extends JdbcDaoSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger("dev.lpf.db.c3p0.demo.C3p0Utils");

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<User> getAll() {
        return this.getJdbcTemplate().query("select * from t_user", new UserMapper());
    }

    /**
     * Getby id user.
     *
     * @param id the id
     * @return the user
     */
    public User getbyId(int id) {
        String sql = "select* from t_user where user_id=?";
        User user = (User) this.getJdbcTemplate().queryForObject(sql, new Object[] {id}, new UserMapper());
        return user;
    }

    /**
     * 增加金额.
     *
     * @param id the id
     * @param money the money
     * @return the boolean
     */
    public boolean increase(int id, float money) {
        String sql = "UPDATE t_user SET account=account+? WHERE user_id=?";
        return this.getJdbcTemplate().update(sql,new Object[] {money,id}) > 0 ? true : false;
    }

    /**
     * 减少金额
     *
     * @param id the id
     * @param money the money
     * @return the boolean
     */
    public boolean decrease(int id, float money) {
        String sql = "UPDATE t_user SET account=account-? WHERE user_id=?";
        return this.getJdbcTemplate().update(sql,new Object[] {money,id}) > 0 ? true : false;
    }
}
