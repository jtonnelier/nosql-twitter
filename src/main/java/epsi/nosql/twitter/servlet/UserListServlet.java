package main.java.epsi.nosql.twitter.servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import main.java.epsi.nosql.twitter.bean.UserBean;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Userlist", urlPatterns={"/list"})
public class UserListServlet extends HttpServlet {

    Logger LOGGER = Logger.getLogger(UserListServlet.class);
    private FreemarkerInstanciation freemarker;

    /**
     * Init du servlet
     * @param config
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        this.freemarker = new FreemarkerInstanciation();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("POST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userChoice = request.getParameter("listType");
        LOGGER.info("Get list " + userChoice);
        HttpSession session = request.getSession();
        UserBean user = (UserBean) session.getAttribute("user");
        //Tout est deja creer à l'index
        ArrayList<UserBean> listToShow = new ArrayList<UserBean>();
        //En fonction du parametre on affichera les followers ou les followings
        if(userChoice.equalsIgnoreCase(Constantes.LIST_FOLLOWERS)){
            listToShow = user.getFollowers();
        }
        else if(userChoice.equalsIgnoreCase(Constantes.LIST_FOLLOWINGS)){
            listToShow = user.getFollowing();
        }
        request.setAttribute("UserList", listToShow);

        //Renvois Freemarker
        //Compilation du FTl, car impossible de repondre par le dispacher... Totalement Beurk
        Configuration cfg = freemarker.getFreemarkerCfg();
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("user", user);
        root.put("listType", userChoice);
        StringWriter out = new StringWriter();
        try {
            Template freemarkerTemplate = cfg.getTemplate("list.ftl");
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
}
