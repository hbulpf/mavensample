package dev.db.c3p0.withSpring;

import java.sql.ResultSet;
import java.sql.SQLException;

import dev.db.c3p0.beans.User;
import org.springframework.jdbc.core.RowMapper;

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
