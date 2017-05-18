package pe.edu.upc.dribblers.backend.models;

import java.util.Date;

/**
 * Created by herrmartell on 5/16/17.
 */

public class TrainingActivity {

    private int mShootCount;
    private boolean mWithTime;
    private Date mExpectedTime;
    private Date mTimestamp;
    private TrainingPlan mTrainingPlan;
    private int mAsserts;
    private int mFails;
    private String mTitle;
    private String mCreatedAt;

    public TrainingActivity() {}

    public TrainingActivity(TrainingPlan trainingPlan,
                            int shootCount,
                            boolean withTime,
                            Date expectedTime,
                            Date timestamp) {
        this.mTrainingPlan = trainingPlan;
        this.mShootCount = shootCount;
        this.mWithTime = withTime;
        this.mExpectedTime = expectedTime;
        this.mTimestamp = timestamp;
    }

    public int getShootCount() {
        return mShootCount;
    }

    public TrainingActivity setShootCount(int mShootCount) {
        this.mShootCount = mShootCount;
        return this;
    }

    public boolean getWithTime() {
        return mWithTime;
    }

    public TrainingActivity setWithTime(boolean mWithTime) {
        this.mWithTime = mWithTime;
        return this;
    }

    public Date getExpectedTime() {
        return mExpectedTime;
    }

    public TrainingActivity setExpectedTime(Date mExpectedTime) {
        this.mExpectedTime = mExpectedTime;
        return this;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }

    public TrainingActivity setTimestamp(Date mTimestamp) {
        this.mTimestamp = mTimestamp;
        return this;
    }

    public TrainingPlan getTrainingPlan() {
        return mTrainingPlan;
    }

    public TrainingActivity setTrainingPlan(TrainingPlan mTrainingPlan) {
        this.mTrainingPlan = mTrainingPlan;
        return this;
    }

    public String getTrainingPlanName(TrainingPlan planName) {
        return mTrainingPlan.getPlanName();
    }

    public int getPlanId() {
        return mTrainingPlan.getId();
    }

    public int getAsserts() {
        return mAsserts;
    }

    public TrainingActivity setAsserts(int mAsserts) {
        this.mAsserts = mAsserts;
        return this;
    }

    public int getFails() {
        return mFails;
    }

    public TrainingActivity setFails(int mFails) {
        this.mFails = mFails;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public TrainingActivity setTitle(String mTitle) {
        this.mTitle = mTitle;
        return this;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public TrainingActivity setCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
        return this;
    }
}
