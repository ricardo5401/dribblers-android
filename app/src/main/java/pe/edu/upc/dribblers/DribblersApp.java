package pe.edu.upc.dribblers;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

/**
 * Created by ricardo on 5/12/17.
 */

public class DribblersApp extends SugarApp {
    private static DribblersApp mDribblersApp;

    public DribblersApp() {
        super();
        mDribblersApp = this;
    }

    public static DribblersApp getInstance() {
        return mDribblersApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
