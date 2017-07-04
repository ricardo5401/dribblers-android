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
 * Created by RICHI on 3/07/2017.
 */

public class Activity {
    private int mId;
    private int mUserId;
    private int mTrainingActivityId;
    private int mFailed;
    private int mAsserted;
    private Date mStartedAt;
    private Date mFinishedAt;

    public int getForeId() {
        return mId;
    }

    public Activity setForeId(int mId) {
        this.mId = mId;
        return this;
    }

    public int getUserId() {
        return mUserId;
    }

    public Activity setUserId(int mUserId) {
        this.mUserId = mUserId;
        return this;
    }

    public int getTrainingActivityId() {
        return mTrainingActivityId;
    }

    public Activity setTrainingActivityId(int mTrainingActivityId) {
        this.mTrainingActivityId = mTrainingActivityId;
        return this;
    }

    public int getFailed() {
        return mFailed;
    }

    public Activity setFailed(int mFailed) {
        this.mFailed = mFailed;
        return this;
    }

    public int getAsserted() {
        return mAsserted;
    }

    public Activity setAsserted(int mAsserted) {
        this.mAsserted = mAsserted;
        return this;
    }

    public Date getStartedAt() {
        return mStartedAt;
    }

    public Activity setStartedAt(Date mStartedAt) {
        this.mStartedAt = mStartedAt;
        return this;
    }

    public Date getFinishedAt() {
        return mFinishedAt;
    }

    public Activity setFinishedAt(Date mFinishedAt) {
        this.mFinishedAt = mFinishedAt;
        return this;
    }

    public static Activity build(JSONObject jsonObject){
        if(jsonObject == null) return null;

        try {
            return new Activity()
                    .setForeId(jsonObject.getInt("id"))
                    .setUserId(jsonObject.getInt("user_id"))
                    .setAsserted(jsonObject.getInt("asserted"))
                    .setFailed(jsonObject.getInt("failed"))
                    .setTrainingActivityId(jsonObject.getInt("training_activity_id"))
                    .setStartedAt(Formatter.parseDate(jsonObject.getString("started_at")))
                    .setFinishedAt(Formatter.parseDate(jsonObject.getString("finished_at")));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BUILD_ACTIVITY", "Error: " + e.getMessage());
            return null;
        }
    }

    public static List<Activity> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        int size = jsonArray.length();
        List<Activity> mActivities = new ArrayList<>();
        for(int i = 0; i < size; i++){
            try {
                mActivities.add(Activity.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mActivities;
    }
}
