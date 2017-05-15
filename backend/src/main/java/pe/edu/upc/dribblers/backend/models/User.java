package pe.edu.upc.dribblers.backend.models;

import android.util.Log;

import com.orm.SugarRecord;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ricardo on 5/11/17.
 */

public class User extends SugarRecord implements Serializable {

    int foreId;
    String firstName;
    String lastName;
    String token;
    String email;

    public User() {
    }

    public static User build(JSONObject mJSONObject) {
        User user = null;
        try {
            user = new User()
                    .setEmail(mJSONObject.getString("email"))
                    .setFirstName(mJSONObject.getString("first_name"))
                    .setLastName(mJSONObject.getString("last_name"))
                    .setForeId(mJSONObject.getInt("id"))
                    .setToken(mJSONObject.getString("token"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BUILD_USER", e.getMessage());
        }
        return user;
    }

    public static User findByEmail(String email) {
        List<User> users = User.find(User.class, "email = ?", email);
        return (users.size() > 0) ? users.get(0) : null;
    }

    public int getForeId() {
        return foreId;
    }

    public User setForeId(int foreId) {
        this.foreId = foreId;
        return this;
    }

    public String getFirstName() {
        return firstName != null ? firstName : "";
    }

    public User setFirstName(String mFirstName) {
        this.firstName = mFirstName;
        return this;
    }

    public String getLastName() {
        return lastName != null ? lastName : "";
    }

    public User setLastName(String mLastName) {
        this.lastName = mLastName;
        return this;
    }

    public String getToken() {
        return token != null ? token : "";
    }

    public User setToken(String mToken) {
        this.token = mToken;
        return this;
    }

    public String getEmail() {
        return email != null ? email : "";
    }

    public User setEmail(String mEmail) {
        this.email = mEmail;
        return this;
    }
}
