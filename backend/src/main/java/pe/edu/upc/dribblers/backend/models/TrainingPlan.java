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

    private String mName;
    private String mDescription;
    private String mPictureUrl;
    private Integer mId;

    public TrainingPlan() {}

    public TrainingPlan(String mName, String mDescription, String mPictureUrl) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mPictureUrl = mPictureUrl;
    }

    public String getName() {
        return mName;
    }

    public TrainingPlan setName(String mName) {
        this.mName = mName;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public TrainingPlan setDescription(String mShootType) {
        this.mDescription = mShootType;
        return this;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public TrainingPlan setPictureUrl(String mPictureUrl) {
        this.mPictureUrl = mPictureUrl;
        return this;
    }

    public Integer getForeId() {
        return mId;
    }

    public TrainingPlan setForeId(Integer mId) {
        this.mId = mId;
        return this;
    }

    public static TrainingPlan build(JSONObject jsonObject){
        try {
            return new TrainingPlan()
                    .setName(jsonObject.getString("name"))
                    .setForeId(jsonObject.getInt("id"))
                    .setDescription(jsonObject.getString("description"))
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
