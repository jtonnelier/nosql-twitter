package main.java.epsi.nosql.twitter.dao;

import main.java.epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;

public class FollowDao {

    private final static Logger log = Logger.getLogger(FollowDao.class);

    /**
     * Get all the followers of user
     * @param user
     * @return
     */
    public static List<String> getFollower(String user){
        Jedis jedis = RedisDao.getInstance();

        String key = Constantes.USER_KEY_FIELD + user + Constantes.FOLLOWER_KEY_FIELD;

        return jedis.lrange(key, 0, jedis.llen(key));
    }

    /**
     * Get all the user followed by user
     * @param user
     * @return
     */
    public static List<String> getFollowing(String user) {
        Jedis jedis = RedisDao.getInstance();

        String key = Constantes.USER_KEY_FIELD + user + Constantes.FOLLOWING_KEY_FIELD;

        return jedis.lrange(key, 0, jedis.llen(key));
    }

}
