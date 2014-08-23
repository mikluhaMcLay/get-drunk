package org.startup.db;

import org.springframework.jdbc.core.RowMapper;
import org.startup.entity.Alcohol;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlcoholMapper implements RowMapper<Alcohol> {
    @Override
    public Alcohol mapRow( ResultSet rs, int rowNum ) throws SQLException {
        return null;
    }
}
