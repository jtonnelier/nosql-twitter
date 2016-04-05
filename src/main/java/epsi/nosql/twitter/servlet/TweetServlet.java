package main.java.epsi.nosql.twitter.servlet;

import main.java.epsi.nosql.twitter.dao.FollowDao;
import main.java.epsi.nosql.twitter.dao.TweetDao;
import main.java.epsi.nosql.twitter.utils.Constantes;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TweetServlet", urlPatterns = {"/tweet"})
public class TweetServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(TweetServlet.class);

    /**
     * Post a tweet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("POST: Post a tweet");

        String tweetMsg = request.getParameter("tweetMsg");
        String loginUser = (String) request.getSession().getAttribute(Constantes.LOGIN);

        TweetDao.newTweet(loginUser, tweetMsg);

        response.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("GET");
    }
}
