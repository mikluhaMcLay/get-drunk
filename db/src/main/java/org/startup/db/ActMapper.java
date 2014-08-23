package org.startup.db;

import org.springframework.jdbc.core.RowMapper;
import org.startup.entity.Act;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActMapper implements RowMapper<Act>{
    @Override
    public Act mapRow( ResultSet rs, int rowNum ) throws SQLException {
        return null;
    }
}
