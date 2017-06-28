package pe.edu.upc.dribblers.backend.network;

/**
 * Created by ricardo on 5/11/17.
 */

public class DribblersAPI {
    static String BASE = "http://dribblers.herokuapp.com";
    static String VERSION = "/v1";
    public static String AUTHORIZE_URL = BASE + "/authorize/social";
    public static String TRAINING_PLAN_URL = BASE + VERSION + "/training_plans";
    public static String TRAINING_ACTIVITY_URL = BASE + VERSION + "/training_activities";
}
