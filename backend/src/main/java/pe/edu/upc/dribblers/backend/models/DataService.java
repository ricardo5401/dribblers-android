package pe.edu.upc.dribblers.backend.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by herrmartell on 5/16/17.
 */

public class DataService {

    private List<TrainingActivity> mTrainingActivities;

    public DataService() {
        mTrainingActivities = new ArrayList<>();
        mTrainingActivities.add(new TrainingActivity(
                getTrainingPlans().get(0), 40, true, new Date(), new Date()));
        mTrainingActivities.add(new TrainingActivity(
                getTrainingPlans().get(1), 25, false, new Date(), new Date()));
    }

    public List<TrainingPlan> getTrainingPlans() {
        List<TrainingPlan> trainingPlans = new ArrayList<>();
        trainingPlans.add(new TrainingPlan(0, "Práctica de ejemplo"));
        trainingPlans.add(new TrainingPlan(1, "Torneo galáctico"));
        return trainingPlans;
    }

    public List<TrainingActivity> getTrainingActivities() { return mTrainingActivities; }

    public boolean addTrainingActivity(TrainingActivity trainingActivity) {
        mTrainingActivities.add(trainingActivity);
        return true;
    }

    public int getActivitiesCount() { return getTrainingActivities().size(); }

}
