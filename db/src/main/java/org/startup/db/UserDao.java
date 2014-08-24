package org.startup.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.startup.entity.User;

import javax.annotation.PostConstruct;
import java.util.List;

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
                new UserProfileMapper());
    }

    public List<User> getChampions() {
        return jdbcTemplate.query("SELECT\n" +
                        "  al.*, sum(a.volume * ai.degree) as champValue\n" +
                        "FROM alcoholic al JOIN act a ON (al.id = a.alcoholic)\n" +
                        "  JOIN alcohol_item ai ON (a.item = ai.id)\n" +
                        "  group by al.id\n" +
                        "  order by champValue\n" +
                        "  limit 3",
                new ChampionUserMapper());
    }

    public List<User> getAlcoCoLikers( long alcoID ) {
        return jdbcTemplate.query("SELECT\n" +
                        "  al.*,\n" +
                        "  sum(a.volume) as totalVolume\n" +
                        "FROM alcoholic al JOIN act a ON (al.id = a.alcoholic)\n" +
                        "  where a.item = ?\n" +
                        "group by al.id\n" +
                        "ORDER BY totalVolume\n" +
                        "LIMIT 3",
                new Object[]{alcoID},
                new AlcoCoLikerMapper());
    }

    public Integer getDrunkTimes(long userID) {
        return jdbcTemplate.queryForObject("select count(*) as drunkTimes\n" +
                        "  from alcoholic al JOIN act a ON (al.id = a.alcoholic)\n" +
                        "  where al.id = ?", new Object[]{userID}, Integer.class);
    }

    public Integer getDrunkThisWeek(long userID) {
        return jdbcTemplate.queryForObject("select sum(a.volume)\n" +
                "  from alcoholic al JOIN act a ON (al.id = a.alcoholic)\n" +
                "  where al.id = ? and (7*24*60*60 > EXTRACT(EPOCH FROM (current_timestamp - a.ts)))", new Object[]{userID}, Integer.class);
    }

    public Integer getDrunkAllTime(long userID) {
        return jdbcTemplate.queryForObject("select sum(a.volume)\n" +
                "  from alcoholic al JOIN act a ON (al.id = a.alcoholic)\n" +
                "  where al.id = ?", new Object[]{userID}, Integer.class);
    }

    public String getFavouriteDrink(long userID) {
        return jdbcTemplate.queryForObject("ai.name\n" +
                "  from alcoholic al JOIN act a ON (al.id = a.alcoholic)\n" +
                "    join alcohol_item ai ON (a.item = ai.id)\n" +
                "    where al.id = ?\n" +
                "  group by a.item, ai.name\n" +
                "  order by itemCount desc limit 1", new Object[]{userID}, String.class);
    }
}
