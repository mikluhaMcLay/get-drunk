package org.startup.db;

import org.startup.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tatyanadembelova on 8/24/14.
 */
public class ChampionUserMapper extends UserProfileMapper {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = super.mapRow(rs, rowNum);
        if (user != null) {
            user.setChampValue(rs.getInt("champValue"));
        }
        return user;
    }
}
