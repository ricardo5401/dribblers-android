package pe.edu.upc.dribblers.backend.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herrmartell on 5/16/17.
 */

public class TrainingPlan {

    private int mIdUser;
    private String mPlanName;
    private String mName;
    private String mShootType;
    private String mPictureUrl;

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

    public String getName() {
        return mName;
    }

    public TrainingPlan setName(String mNanme) {
        this.mName = mNanme;
        return this;
    }

    public String getShootType() {
        return mShootType;
    }

    public TrainingPlan setShootType(String mShootType) {
        this.mShootType = mShootType;
        return this;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public TrainingPlan setPictureUrl(String mPictureUrl) {
        this.mPictureUrl = mPictureUrl;
        return this;
    }

    public static TrainingPlan build(JSONObject jsonObject){
        try {
            return new TrainingPlan()
                    .setName(jsonObject.getString("name"))
                    .setShootType(jsonObject.getString("shoot_type"))
                    .setPictureUrl(jsonObject.getString("picture_url"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("TRAINING_PLAN", "error on build json: " + e.getMessage());
            return null;
        }
    }

    public static List<TrainingPlan> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        List<TrainingPlan> mTrainingPlans = new ArrayList<>();
        int length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                mTrainingPlans.add(TrainingPlan.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mTrainingPlans;
    }
}
