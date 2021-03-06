package org.startup.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.startup.entity.Act;
import org.startup.entity.Alcohol;
import org.startup.entity.User;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class ActDao {
    private static final Logger log = LoggerFactory.getLogger( ActDao.class );

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute( "SELECT 1" );
        log.info( "All right" );
    }

    public User getAct( long id ) {
        throw new UnsupportedOperationException();
    }

    public void saveAct(Act act) {
        int itemID = getAlcoholItem(act.getAlcohol());
        jdbcTemplate.update(
                "insert into act(alcoholic, item, ts, volume, photo, latitude, longitude)\n" +
                "values (?,?,?,?,?,?,?)", new Object[]{(int) act.getUserID(),
                        itemID,
                        new java.sql.Timestamp(new Date().getTime()),
                        act.getVolume(),
                        act.getPhotoLink(),
                        act.getLatitude(),
                        act.getLongitude()
                });
    }

    private int getAlcoholItem(Alcohol alcohol) {
        return 0;
    }

    public void saveUser( User user ) {

    }


}
