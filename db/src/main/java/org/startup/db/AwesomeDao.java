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
public class AwesomeDao {
    private static final Logger log = LoggerFactory.getLogger( AwesomeDao.class );

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
        throw new UnsupportedOperationException();
    }

    public void saveUser( User user ) {

    }


}
