package pe.edu.upc.dribblers.backend.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pe.edu.upc.dribblers.backend.utils.Formatter;

/**
 * Created by RICHI on 3/07/2017.
 */

public class Event implements Serializable {
    private int mId;
    private int mUserId;
    private String mDescription;
    private Date mEventDate;
    private String mPlace;
    private boolean isPublic;

    public int getForeId() {
        return mId;
    }

    public Event setForeId(int foreId) {
        this.mId = foreId;
        return this;
    }

    public int getUserId() {
        return mUserId;
    }

    public Event setUserId(int userId) {
        this.mUserId = userId;
        return this;
    }

    public String getDescription() {
        return mDescription;
    }

    public Event setDescription(String description) {
        this.mDescription = description;
        return this;
    }

    public Date getEventDate() {
        return mEventDate;
    }

    public String getStringDate() { return Formatter.parseDate(this.mEventDate); }

    public Event setEventDate(Date eventDate) {
        this.mEventDate = eventDate;
        return this;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Event setPublic(boolean aPublic) {
        isPublic = aPublic;
        return this;
    }

    public String getmPlace(){ return this.mPlace; }

    public Event setPlace(String mPlace){
        this.mPlace = mPlace;
        return  this;
    }

    public static Event build(JSONObject jsonObject){
        if(jsonObject == null) return null;

        try {
            return new Event()
                    .setDescription(jsonObject.getString("description"))
                    .setPlace(jsonObject.getString("place"))
                    .setForeId(jsonObject.getInt("id"))
                    .setUserId(jsonObject.getInt("user_id"))
                    .setPublic(jsonObject.getBoolean("is_public"))
                    .setEventDate(Formatter.parseDate( jsonObject.getString("event_date") ));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("BUILD_EVENT", "Error on build Event object. Message: " + e.getMessage());
            return null;
        }
    }

    public static List<Event> build(JSONArray jsonArray){
        if(jsonArray == null) return null;

        int size = jsonArray.length();
        List<Event> mEvents = new ArrayList<>();
        for( int i = 0; i < size; i++){
            try {
                mEvents.add(Event.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mEvents;
    }

    public JSONObject serialize(){
        JSONObject jsonEvent = new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject()
            .put("user_id", this.mUserId)
            .put("is_public", this.isPublic)
            .put("description", this.mDescription)
            .put("place", this.mPlace)
            .put("event_date", this.mEventDate);

            return jsonEvent.put("event", jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
