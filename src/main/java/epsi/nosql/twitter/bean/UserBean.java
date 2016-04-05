package main.java.epsi.nosql.twitter.bean;

import java.util.ArrayList;

/**
 * Created by Jocelyn on 04/04/2016.
 */
public class UserBean {
    private String login;
    private ArrayList<UserBean> followers;
    private ArrayList<UserBean> following;
    private ArrayList<String> tweets;

    public UserBean() {
        this.tweets = new ArrayList<String>();
        this.following = new ArrayList<UserBean>();
        this.followers = new ArrayList<UserBean>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<String> getTweets() {
        return tweets;
    }

    public void setTweets(ArrayList<String> tweets) {
        this.tweets = tweets;
    }

    public ArrayList<UserBean> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserBean> followers) {
        this.followers = followers;
    }

    public ArrayList<UserBean> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<UserBean> following) {
        this.following = following;
    }
}
