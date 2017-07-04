package pe.edu.upc.dribblers.backend.models;

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

public class Subscription {
    private int mId;
    private int mUserId;
    private int mAccountTypeId;
    private int mPaymentId;
    private Date mExpiredAt;
    private Date mCreatedAt;

    public int getId() {
        return mId;
    }

    public Subscription setId(int mId) {
        this.mId = mId;
        return this;
    }

    public int getUserId() {
        return mUserId;
    }

    public Subscription setUserId(int mUserId) {
        this.mUserId = mUserId;
        return this;
    }

    public int getAccountTypeId() {
        return mAccountTypeId;
    }

    public Subscription setAccountTypeId(int mAccountTypeId) {
        this.mAccountTypeId = mAccountTypeId;
        return this;
    }

    public int getPaymentId() {
        return mPaymentId;
    }

    public Subscription setPaymentId(int mPaymentId) {
        this.mPaymentId = mPaymentId;
        return this;
    }

    public Date getExpiredAt() {
        return mExpiredAt;
    }

    public Subscription setExpiredAt(Date mExpiredAt) {
        this.mExpiredAt = mExpiredAt;
        return this;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public Subscription setCreatedAt(Date mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
        return this;
    }

    public static Subscription build(JSONObject jsonObject){
        if(jsonObject == null) return null;

        try {
            return new Subscription()
                    .setUserId(jsonObject.getInt("id"))
                    .setAccountTypeId(jsonObject.getInt("account_type_id"))
                    .setId(jsonObject.getInt("id"))
                    .setPaymentId(jsonObject.getInt("payment_id"))
                    .setCreatedAt(Formatter.parseDate(jsonObject.getString("created_at")))
                    .setExpiredAt(Formatter.parseDate(jsonObject.getString("expired_at")));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Subscription> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        int size = jsonArray.length();
        List<Subscription> mSubscriptions = new ArrayList<>();
        for(int i = 0; i < size; i++){
            try {
                mSubscriptions.add(Subscription.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mSubscriptions;
    }
}
