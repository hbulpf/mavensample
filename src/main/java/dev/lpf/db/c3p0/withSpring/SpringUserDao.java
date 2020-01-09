
package dev.lpf.db.c3p0.withSpring;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dev.lpf.db.c3p0.beans.User;

/**
 * Spring UserDao
 */
public class SpringUserDao extends JdbcDaoSupport {
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
}
