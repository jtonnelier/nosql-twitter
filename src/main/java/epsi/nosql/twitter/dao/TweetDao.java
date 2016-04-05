package main.java.epsi.nosql.twitter.dao;

import main.java.epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TweetDao extends RedisDao{

    private final static Logger LOGGER = Logger.getLogger(TweetDao.class);

    public static void newTweet(String login, String tweetMsg) {
        Jedis jedis = getInstance();

        String defTweet = login + " a tweeté : <br>" + tweetMsg;

        jedis.lpush(Constantes.USER_KEY_FIELD + login + Constantes.TWEETS_KEY_FIELD, defTweet);

        LOGGER.info("Ajout du tweet : '"+tweetMsg+"' pour le user "+ login);
    }

    /**
     * Get all the tweets of a user
     * @param login
     * @return
     */
    public static List<String> getAllTweets(String login) {
        Jedis jedis = getInstance();

        String key = Constantes.USER_KEY_FIELD + login + Constantes.TWEETS_KEY_FIELD;

        LOGGER.info("Demande de tous les tweets de "+ login);

        return jedis.lrange(key ,0, jedis.llen(key));
    }

    /**
     * Recuperation de toute les cle user:tweet pour recherche
     * hastag
     * @return
     */
    private static Set<String> getKeyTweets(){
        Jedis jedis = getInstance();

        String pattern = Constantes.REDIS_JOKER + Constantes.TWEETS_KEY_FIELD;

        LOGGER.info("Recuperation des cles pour les tweets");

        return jedis.keys(pattern);
    }

    /**
     * Retourne tout les tweets de la base
     * @return
     */
    public static List<String> getAllTweetsByHastag(String hastag) {
        Jedis jedis = getInstance();

        Set<String> allKeys = getKeyTweets();
        List<String> allTweetsDatabase = new ArrayList<String>();
        for(String key: allKeys){
            List<String> userTweets = jedis.lrange(key ,0, jedis.llen(key));
            for(String tweet: userTweets){
                if(tweet.contains(hastag)){
                    allTweetsDatabase.add(tweet);
                }
            }
        }
        return allTweetsDatabase;
    }
}
