package pe.edu.upc.dribblers;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

import java.util.List;

import pe.edu.upc.dribblers.backend.models.DataService;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;

/**
 * Created by ricardo on 5/12/17.
 */

public class DribblersApp extends SugarApp {

    private static DribblersApp mDribblersApp;
    private DataService dataService = new DataService();

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

    public List<TrainingActivity> getTrainingActivities() {
        return dataService.getTrainingActivities();
    }

    public boolean addTrainingActivity(TrainingActivity trainingActivity) {
        return dataService.addTrainingActivity(trainingActivity);
    }

    public List<TrainingPlan> getTrainingPlans() {
        return dataService.getTrainingPlans();
    }

}
