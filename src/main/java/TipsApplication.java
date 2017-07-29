import index.IndexController;

import static spark.Spark.get;
import static spark.Spark.port;

public class TipsApplication {
    public static void main(String[] args) {
        port(4567);
        get("/", IndexController.get);
    }
}
