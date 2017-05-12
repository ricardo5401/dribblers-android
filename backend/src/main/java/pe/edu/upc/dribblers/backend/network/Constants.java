package pe.edu.upc.dribblers.backend.network;

/**
 * Created by ricardo on 5/11/17.
 */

public class Constants {
    public static class Google {
        public static String CLIENT_ID = "533952468325-65no9j35fhg4c409qfkct36jkg9rpdi9.apps.googleusercontent.com";
    }
    public static class Server {
        static String BASE = "http://dribblers.herokuapp.com/";
        public static String AUTHORIZE_URL = BASE + "/authorize/social";
    }
}
