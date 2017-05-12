package pe.edu.upc.dribblers.backend.network;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import pe.edu.upc.dribblers.backend.models.User;

/**
 * Created by ricardo on 5/12/17.
 */

public class Facebook {

    public static List<String> ReadPermission(){
        return Arrays.asList("public_profile", "email");
    }

    public static User SignInResult(JSONObject result){
        User user = null;
        try {
            user = new User()
                    .setFirstName(result.getString("first_name"))
                    .setLastName(result.getString("last_name"))
                    .setEmail(result.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Bundle Parameters(){
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,first_name,last_name,email");
        return parameters;
    }
}
