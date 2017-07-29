package index;

import spark.Request;
import spark.Response;
import spark.Route;
import util.View;

import java.util.HashMap;
import java.util.Map;

public class IndexController {
    public static Route get = (Request request, Response response) -> {
        Map<String, Object> params = new HashMap<>();
        params.put("Test", "hello");
        return View.render(params, "index.vm");
    };
}
