package pe.edu.upc.dribblers;

import com.androidnetworking.AndroidNetworking;
import com.orm.SugarApp;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.dribblers.backend.models.Event;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.DribblersAPI;

/**
 * Created by ricardo on 5/12/17.
 */

public class DribblersApp extends SugarApp {

    private static DribblersApp mDribblersApp;
    private DribblersAPI mDribblersAPI = new DribblersAPI();

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
        return mDribblersAPI.getTrainingActivities();
    }

    public boolean addTrainingActivity(TrainingActivity trainingActivity) {
        return mDribblersAPI.addTrainingActivity(trainingActivity);
    }

    public void setCurrentUser(User user){ mDribblersAPI.setCurrentUser(user); }

    public User getCurrentUser(){ return mDribblersAPI.getCurrentUser(); }

    public void setCurrentPlan(TrainingPlan trainingPlan){ mDribblersAPI.setCurrentPlan(trainingPlan); }

    public void setCurrentEvent(Event mEvent){ mDribblersAPI.setCurrentEvent(mEvent); }

    public Event getCurrentEvent() { return mDribblersAPI.getCurrentEvent(); }

    public TrainingPlan getCurrentPlan(){ return mDribblersAPI.getCurrentPlan(); }

    public List<TrainingPlan> getTrainingPlans() {
        return new ArrayList<TrainingPlan>();
    }

}
