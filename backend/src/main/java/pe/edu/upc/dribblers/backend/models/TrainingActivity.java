package pe.edu.upc.dribblers.backend.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.edu.upc.dribblers.backend.utils.Formatter;

/**
 * Created by herrmartell on 5/16/17.
 */

public class TrainingActivity {

    private int mShootCount;
    private boolean mWithTime;
    private Date mExpectedTime;
    private Date mCreatedAt;
    private int mTrainingPlanId;
    private String mDescription;
    private String mPictureUrl;
    private String mName;
    private Integer mId;

    public TrainingActivity() {}


    public int getShootCount() {
        return mShootCount;
    }

    public TrainingActivity setShootCount(int mShootCount) {
        this.mShootCount = mShootCount;
        return this;
    }

    public boolean isWithTime() {
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

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public TrainingActivity setCreatedAt(Date mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
        return this;
    }

    public int getTrainingPlanId() {
        return mTrainingPlanId;
    }

    public TrainingActivity setTrainingPlanId(int mTrainingPlanId) {
        this.mTrainingPlanId = mTrainingPlanId;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public TrainingActivity setDescription(String mDescription) {
        this.mDescription = mDescription;
        return this;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public TrainingActivity setPictureUrl(String mPictureUrl) {
        this.mPictureUrl = mPictureUrl;
        return this;
    }

    public String getName() {
        return mName;
    }

    public TrainingActivity setName(String mName) {
        this.mName = mName;
        return this;
    }

    public Integer getForeId() {
        return mId;
    }

    public TrainingActivity setForeId(Integer mId) {
        this.mId = mId;
        return this;
    }

    public static TrainingActivity build(JSONObject jsonObject){
        if(jsonObject == null) return null;

        try {
            return new TrainingActivity()
                    .setForeId(jsonObject.getInt("id"))
                    .setDescription(jsonObject.getString("description"))
                    .setName(jsonObject.getString("name"))
                    .setPictureUrl(jsonObject.getString("picture_url"))
                    .setShootCount(jsonObject.getInt("shoot_count"))
                    .setTrainingPlanId(jsonObject.getInt("training_plan_id"))
                    .setWithTime(jsonObject.getBoolean("with_time"))
                    .setCreatedAt(Formatter.parseDate(jsonObject.getString("created_at")))
                    .setExpectedTime(Formatter.parseDate(jsonObject.getString("expected_time")));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BUILD_TRAINING_ACTIVITY", "Error on building: "+ e.getMessage());
            return null;
        }
    }
    public static List<TrainingActivity> build(JSONArray jsonArray){
        List<TrainingActivity> mActivities = new ArrayList<>();
        if(jsonArray == null) return mActivities;

        int length = jsonArray.length();
        for (int i = 0; i < length; i++){
            try {
                mActivities.add(TrainingActivity.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mActivities;
    }

}
