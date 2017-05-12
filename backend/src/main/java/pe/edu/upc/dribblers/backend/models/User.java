package pe.edu.upc.dribblers.backend.models;

/**
 * Created by ricardo on 5/11/17.
 */

public class User {
    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mToken;
    private String mEmail;

    public int getId() {
        return mId;
    }

    public User setId(int mId) {
        this.mId = mId;
        return this;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public User setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
        return this;
    }

    public String getLastName() {
        return mLastName;
    }

    public User setLastName(String mLastName) {
        this.mLastName = mLastName;
        return this;
    }

    public String getToken() {
        return mToken;
    }

    public User setToken(String mToken) {
        this.mToken = mToken;
        return this;
    }

    public String getEmail() {
        return mEmail;
    }

    public User setEmail(String mEmail) {
        this.mEmail = mEmail;
        return this;
    }
}
