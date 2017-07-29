package index;

import com.google.common.collect.ImmutableMap;
import spark.Request;
import spark.Response;
import spark.Route;
import util.View;

public class IndexController {
    public static Route get = (Request request, Response response) -> {
        ImmutableMap.Builder<String, Object> params = new ImmutableMap.Builder<>();
        params.put("test", "hello");
        return View.render(params.build(), "index.vm");
    };
}
