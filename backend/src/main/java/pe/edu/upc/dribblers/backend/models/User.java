package pe.edu.upc.dribblers.backend.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ricardo on 5/11/17.
 */

public class User implements Serializable {
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mToken;
    private String mEmail;

    public User(){
        mId = 0;
        mFirstName = "";
        mLastName = "";
        mEmail = "";
        mToken = "";
    }

    public int getId() {
        return mId;
    }

    public User setId(int mId) {
        this.mId = mId;
        return this;
    }

    public String getFirstName() {
        return mFirstName != null ? mFirstName : "";
    }

    public User setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
        return this;
    }

    public String getLastName() {
        return mLastName != null ? mLastName : "";
    }

    public User setLastName(String mLastName) {
        this.mLastName = mLastName;
        return this;
    }

    public String getToken() {
        return mToken != null ? mToken : "";
    }

    public User setToken(String mToken) {
        this.mToken = mToken;
        return this;
    }

    public String getEmail() {
        return mEmail != null ? mEmail : "";
    }

    public User setEmail(String mEmail) {
        this.mEmail = mEmail;
        return this;
    }

    public static User build(JSONObject mJSONObject){
        User user = null;
        try {
            user = new User()
                    .setEmail(mJSONObject.getString("email"))
                    .setFirstName(mJSONObject.getString("first_name"))
                    .setLastName(mJSONObject.getString("last_name"))
                    .setId(mJSONObject.getInt("id"))
                    .setToken(mJSONObject.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BUILD_USER", e.getMessage());
        }
        return user;
    }
}
