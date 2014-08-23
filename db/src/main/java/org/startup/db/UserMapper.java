package org.startup.db;

import org.springframework.jdbc.core.RowMapper;
import org.startup.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow( ResultSet rs, int rowNum ) throws SQLException {
        return null;
    }
}
