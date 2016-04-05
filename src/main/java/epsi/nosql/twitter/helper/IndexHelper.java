package main.java.epsi.nosql.twitter.helper;

import main.java.epsi.nosql.twitter.bean.UserBean;
import main.java.epsi.nosql.twitter.dao.FollowDao;
import main.java.epsi.nosql.twitter.dao.TweetDao;

import java.util.ArrayList;
import java.util.List;

/**
 * helper utilise a l'index
 */
public class IndexHelper {

    public IndexHelper() {
    }

    public UserBean getInfosUser(String login){
        UserBean user = new UserBean();
        //Double info?
        user.setLogin(login);
        //Recuperation des tweets
        user.setTweets(new ArrayList<String>(TweetDao.getAllTweets(login)));
        //Recuperation des stats
        user.setFollowing(this.getFollowing(login));
        user.setFollowers(this.getFollowers(login));
        return user;
    }

    /**
     * recuperation des followers
     */
    private ArrayList<UserBean> getFollowers(String login){
        //On commence par recuperer les followers
        ArrayList<UserBean> followers = new ArrayList<UserBean>();
        List<String> listIdfollowers = FollowDao.getFollower(login);
        //Pour chaque follower on va recuperer ces tweets
        for(String followerId: listIdfollowers){
            UserBean followerBean = new UserBean();
            followerBean.setLogin(followerId);
            followerBean.setTweets(new ArrayList<String>(TweetDao.getAllTweets(followerId)));
            followers.add(followerBean);
        }
        return followers;
    }

    /**
     * recuperation des followings
     */
    private ArrayList<UserBean> getFollowing(String login){
        ArrayList<UserBean> followings = new ArrayList<UserBean>();
        List<String> listIdsFollowing = FollowDao.getFollowing(login);
        //Pour chaque follower on va recuperer ces tweets
        for(String followingId: listIdsFollowing){
            UserBean followingBean = new UserBean();
            followingBean.setLogin(followingId);
            followingBean.setTweets(new ArrayList<String>(TweetDao.getAllTweets(followingId)));
            followings.add(followingBean);
        }
        return followings;
    }
}
