package main.java.epsi.nosql.twitter.dao;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by jtonneli.
 */
public class RedisDao {

    private static Jedis jedis;
    private static final Logger log = Logger.getLogger(RedisDao.class);

    public static Jedis getInstance(){
        if(jedis == null) {
            try {
                jedis = new Jedis("pub-redis-19899.us-east-1-4.6.ec2.redislabs.com", 19899);
                jedis.auth("twitter");
                log.info("Connected to Redis");
            } catch (Exception e) {
                log.error("Failed to connect to Redis");
            }
        }
        return jedis;
    }
}
