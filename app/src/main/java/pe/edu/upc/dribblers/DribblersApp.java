package pe.edu.upc.dribblers;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.dribblers.backend.models.DataService;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;
import pe.edu.upc.dribblers.backend.models.User;

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

    public void setCurrentUser(User user){ dataService.setCurrentUser(user); }

    public User getCurrentUser(){ return dataService.getCurrentUser(); }

    public void setCurrentPlan(TrainingPlan trainingPlan){ dataService.setCurrentPlan(trainingPlan); }

    public TrainingPlan getCurrentPlan(){ return dataService.getCurrentPlan(); }

    public List<TrainingPlan> getTrainingPlans() {
        return new ArrayList<TrainingPlan>();
    }

}
