package util;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.Map;

public class View {
    private View() {}

    public static String render(Map<String, Object> params, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(params, templatePath));
    }
}
