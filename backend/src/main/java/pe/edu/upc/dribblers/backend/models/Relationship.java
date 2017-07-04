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

public class Relationship {
    private int mId;
    private int mUserId;
    private int mFriendId;
    private int mStatus;
    private Date mCreatedAt;
    private Date mUpdatedAt;

    public int getForeId() {
        return mId;
    }

    public Relationship setForeId(int mId) {
        this.mId = mId;
        return this;
    }

    public int getUserId() {
        return mUserId;
    }

    public Relationship setUserId(int mUserId) {
        this.mUserId = mUserId;
        return this;
    }

    public int getFriendId() {
        return mFriendId;
    }

    public Relationship setFriendId(int mFriendId) {
        this.mFriendId = mFriendId;
        return this;
    }

    public int getStatus() {
        return mStatus;
    }

    public Relationship setStatus(int mStatus) {
        this.mStatus = mStatus;
        return this;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public Relationship setCreatedAt(Date mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
        return this;
    }

    public Date getUpdatedAt() {
        return mUpdatedAt;
    }

    public Relationship setUpdatedAt(Date mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
        return this;
    }

    public static Relationship build(JSONObject jsonObject){
        if(jsonObject == null) return null;

        try {
            return new Relationship()
                    .setUserId(jsonObject.getInt("user_id"))
                    .setForeId(jsonObject.getInt("id"))
                    .setCreatedAt(Formatter.parseDate(jsonObject.getString("created_at")))
                    .setUpdatedAt(Formatter.parseDate(jsonObject.getString("updated_at")))
                    .setFriendId(jsonObject.getInt("friend_id"))
                    .setStatus(jsonObject.getInt("status"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("RELATIONSHIP_BUILD", "Error: " + e.getMessage());
            return null;
        }
    }

    public static List<Relationship> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        int size = jsonArray.length();
        List<Relationship> mRelationships = new ArrayList<>();
        for(int i = 0; i < size; i++){
            try {
                mRelationships.add(Relationship.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mRelationships;
    }
}
