package pe.edu.upc.dribblers.backend.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RICHI on 3/07/2017.
 */

public class AccountType {
    private int mId;
    private String mName;
    private String mDescription;
    private double mSubscriptionPriceAnnuallyPayment;
    private double mSubscriptionPriceMonthlyPayment;

    public int getForeId() {
        return mId;
    }

    public AccountType setForeId(int mId) {
        this.mId = mId;
        return this;
    }

    public String getName() {
        return mName;
    }

    public AccountType setName(String mName) {
        this.mName = mName;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public AccountType setDescription(String mDescription) {
        this.mDescription = mDescription;
        return this;
    }

    public double getSubscriptionPriceAnnuallyPayment() {
        return mSubscriptionPriceAnnuallyPayment;
    }

    public AccountType setSubscriptionPriceAnnuallyPayment(double mSubscriptionPriceAnnuallyPayment) {
        this.mSubscriptionPriceAnnuallyPayment = mSubscriptionPriceAnnuallyPayment;
        return this;
    }

    public double getSubscriptionPriceMonthlyPayment() {
        return mSubscriptionPriceMonthlyPayment;
    }

    public AccountType setSubscriptionPriceMonthlyPayment(double mSubscriptionPriceMonthlyPayment) {
        this.mSubscriptionPriceMonthlyPayment = mSubscriptionPriceMonthlyPayment;
        return this;
    }

    public static AccountType build(JSONObject jsonObject){
        if(jsonObject==null) return null;

        try {
            return new AccountType()
                    .setForeId(jsonObject.getInt("id"))
                    .setName(jsonObject.getString("name"))
                    .setDescription(jsonObject.getString("description"))
                    .setSubscriptionPriceAnnuallyPayment(jsonObject.getDouble("subscription_price_annually_payment"))
                    .setSubscriptionPriceMonthlyPayment(jsonObject.getDouble("subscription_price_monthly_payment"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static List<AccountType> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        int size = jsonArray.length();
        List<AccountType> mAccountTypes = new ArrayList<>();
        for(int i = 0; i < size; i++){
            try {
                mAccountTypes.add(AccountType.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mAccountTypes;
    }
}
