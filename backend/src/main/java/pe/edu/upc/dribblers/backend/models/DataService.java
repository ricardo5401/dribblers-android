package pe.edu.upc.dribblers.backend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by herrmartell on 5/16/17.
 */

public class DataService {

    private List<TrainingActivity> mTrainingActivities;
    private User mUser;
    private TrainingPlan mTrainingPlan;

    public DataService() {
    }

    public List<TrainingActivity> getTrainingActivities() { return mTrainingActivities; }

    public boolean addTrainingActivity(TrainingActivity trainingActivity) {
        mTrainingActivities.add(trainingActivity);
        return true;
    }

    public User getCurrentUser(){
        return mUser;
    }

    public void setCurrentUser(User user){
        this.mUser = user;
    }

    public TrainingPlan getCurrentPlan(){ return mTrainingPlan; }

    public void setCurrentPlan(TrainingPlan trainingPlan){ this.mTrainingPlan = trainingPlan; }

    public int getActivitiesCount() { return getTrainingActivities().size(); }

}
