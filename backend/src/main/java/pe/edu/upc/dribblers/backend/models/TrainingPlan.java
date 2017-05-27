package pe.edu.upc.dribblers.backend.models;

/**
 * Created by herrmartell on 5/16/17.
 */

public class TrainingPlan {

    private int mIdUser;
    private String mPlanName;

    public TrainingPlan() {}

    public TrainingPlan(int mId, String mPlanName) {
        this.mIdUser = mId;
        this.mPlanName = mPlanName;
    }

    public int getId() {
        return mIdUser;
    }

    public TrainingPlan setId(int mId) {
        this.mIdUser = mId;
        return this;
    }

    public String getPlanName() {
        return mPlanName;
    }

    public TrainingPlan setPlanName(String mPlanName) {
        this.mPlanName = mPlanName;
        return this;
    }

}
