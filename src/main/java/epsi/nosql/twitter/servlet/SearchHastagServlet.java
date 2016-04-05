package main.java.epsi.nosql.twitter.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import main.java.epsi.nosql.twitter.bean.UserBean;
import main.java.epsi.nosql.twitter.dao.TweetDao;
import main.java.epsi.nosql.twitter.helper.IndexHelper;
import main.java.epsi.nosql.twitter.utils.Constantes;
import main.java.epsi.nosql.twitter.utils.FreemarkerInstanciation;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jocelyn
 */
@WebServlet(name = "SearchHastagServlet", urlPatterns = {"/search"})
public class SearchHastagServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(SearchHastagServlet.class);

    private FreemarkerInstanciation freemarker;

    /**
     * Init du servlet
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        this.freemarker = new FreemarkerInstanciation();
    }

    /**
     * Get all tweet with a parameter hashtag
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String hashtag = request.getParameter("hashtag");
        LOGGER.info("POST: Find all tweet with: " + hashtag);
        List<String> tweets = TweetDao.getAllTweetsByHastag(hashtag);
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        //Compilation du FTl, car impossible de repondre par le dispacher... Totalement Beurk
        Configuration cfg = freemarker.getFreemarkerCfg();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("tweets", tweets);
        root.put("hashtag", hashtag);
        root.put("user", user);
        StringWriter out = new StringWriter();
        try {
            Template freemarkerTemplate = cfg.getTemplate("search.ftl");
            freemarkerTemplate.process(root, out);
        } catch (IOException e) {
            LOGGER.debug("Erreur Freemarker: process template");
            e.printStackTrace();
        } catch (TemplateException e) {
            LOGGER.debug("Erreur Freemarker: recuperation template");
            e.printStackTrace();
        }
        response.getWriter().write(out.getBuffer().toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("GET");
    }
}
