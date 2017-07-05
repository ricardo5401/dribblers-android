package pe.edu.upc.dribblers.backend.network;

import java.util.List;

import pe.edu.upc.dribblers.backend.models.Event;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;
import pe.edu.upc.dribblers.backend.models.User;

/**
 * Created by ricardo on 5/11/17.
 */

public class DribblersAPI {
    static String BASE = "http://dribblers.herokuapp.com";
    static String VERSION = "/v1";
    public static String AUTHORIZE_URL = BASE + "/authorize/social";
    public static String TRAINING_PLAN_URL = BASE + VERSION + "/training_plans";
    public static String TRAINING_ACTIVITY_URL = BASE + VERSION + "/training_activities";
    public static String EVENT_URL = BASE + VERSION + "/events";

    private List<TrainingActivity> mTrainingActivities;
    private User mUser;
    private TrainingPlan mTrainingPlan;
    private Event mEvent;

    public DribblersAPI() {
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

    public void setCurrentEvent(Event mEvent){ this.mEvent = mEvent; }

    public Event getCurrentEvent(){ return mEvent; }

    public int getActivitiesCount() { return getTrainingActivities().size(); }
}
