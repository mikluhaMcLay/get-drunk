package org.startup.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.startup.entity.User;

import javax.annotation.PostConstruct;

@Component
public class UserDao {
    private static final Logger log = LoggerFactory.getLogger( UserDao.class );

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute( "SELECT 1" );
        log.info( "All right" );
    }

    public User getUser( long id ) {
        return jdbcTemplate.queryForObject("select username, photo from alcoholic where id = ?",
                new Object[]{id},
                new UserRowMapper());
    }

    public void saveUser( User user ) {

    }


}
