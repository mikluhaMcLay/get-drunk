package org.startup.db;

import org.springframework.jdbc.core.RowMapper;
import org.startup.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tatyanadembelova on 8/24/14.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.next()) {
            return new User(rs.getString("username"), rs.getString("photo"));
        }
        return null;
    }
}
