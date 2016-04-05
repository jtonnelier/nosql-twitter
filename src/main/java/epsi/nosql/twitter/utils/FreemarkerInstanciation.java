package main.java.epsi.nosql.twitter.utils;

import freemarker.cache.TemplateNameFormat;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;


/**
 * Created by Jocelyn on 04/04/2016.
 */
public class FreemarkerInstanciation {

    Configuration cfg;

    public Configuration getFreemarkerCfg(){
        if(cfg == null){
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setTemplateNameFormat(TemplateNameFormat.DEFAULT_2_4_0);
            cfg.setClassForTemplateLoading(this.getClass(), "/web");
            this.cfg = cfg;
        }
        return cfg;
    }
}
