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

public class Payment {
    private int mId;
    private int mSubscriptionId;
    private double mPrice;
    private Date mCreatedAt;

    public int getForeId() {
        return mId;
    }

    public Payment setForeId(int mId) {
        this.mId = mId;
        return this;
    }

    public int getSubscriptionId() {
        return mSubscriptionId;
    }

    public Payment setSubscriptionId(int mSubscriptionId) {
        this.mSubscriptionId = mSubscriptionId;
        return this;
    }

    public double getPrice() {
        return mPrice;
    }

    public Payment setPrice(double mPrice) {
        this.mPrice = mPrice;
        return this;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public Payment setCreatedAt(Date mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
        return this;
    }

    public static Payment build(JSONObject jsonObject){
        if(jsonObject==null) return null;

        try {
            return new Payment()
                    .setForeId(jsonObject.getInt("id"))
                    .setSubscriptionId(jsonObject.getInt("subscription_id"))
                    .setPrice(jsonObject.getDouble("price"))
                    .setCreatedAt(Formatter.parseDate(jsonObject.getString("created_at")));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<Payment> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        int size = jsonArray.length();
        List<Payment> mPayments = new ArrayList<>();
        for(int i = 0; i < size; i++){
            try {
                mPayments.add(Payment.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mPayments;
    }
}
