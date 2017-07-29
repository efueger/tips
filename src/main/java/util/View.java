package util;

import com.google.common.collect.ImmutableMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class View {
    private View() {}

    public static String render(ImmutableMap<String, Object> params, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(params, templatePath));
    }
}
