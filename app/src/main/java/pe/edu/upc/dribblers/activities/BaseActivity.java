package pe.edu.upc.dribblers.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.Constants;

/**
 * Created by ricardo on 5/12/17.
 */

public class BaseActivity extends AppCompatActivity {
    Handler toastMessage;
    private ProgressDialog dialog;
    private static final String SIGNIN_TAG = "SIGNIN_USER";
    private static final String LOG_USER = "LOG_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        crearHandlers(this);
    }

    protected void crearHandlers(final Context context){
        toastMessage = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(context, message.obj.toString(), Toast.LENGTH_LONG).show();
            }
        };
    }

    protected void showMessage(String mensaje){
        toastMessage.obtainMessage(1, mensaje).sendToTarget();
    }

    protected void showDialogLoading(String mensaje){
        this.dialog.setMessage(mensaje);
        this.dialog.show();
    }

    protected void hideDialogLoading(){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    protected void signIn(final User user, final boolean redirect){
        //new sign in via social network
        showDialogLoading("Sign In...");
        Log.i(SIGNIN_TAG, "URL: " + Constants.Server.AUTHORIZE_URL);
        AndroidNetworking.post(Constants.Server.AUTHORIZE_URL)
            .addBodyParameter("email", user.getEmail())
            .addBodyParameter("first_name", user.getFirstName())
            .addBodyParameter("last_name", user.getLastName())
            .setTag(SIGNIN_TAG)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    User authenticatedUser = User.build( extractUser(response) );
                    if(authenticatedUser != null && !authenticatedUser.getEmail().isEmpty()){
                        Log.i(SIGNIN_TAG, "Sign in successfully");
                        logUser(authenticatedUser);
                        if(redirect){ goToMain(authenticatedUser); }
                    }else{
                        Log.e(SIGNIN_TAG, "Error on signin");
                        showError("Server error, try again");
                    }
                }
                @Override
                public void onError(ANError error) {
                    manageNetworkError(error, SIGNIN_TAG);
                    showError("Server error, try again");
                }
            });
    }


    private JSONObject extractUser(JSONObject jsonObject){
        try {
            return jsonObject.getJSONObject("user");
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    private void goToMain(User user){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    private void manageNetworkError(ANError error, String TAG){
        Log.e(TAG, "Error code: " + String.valueOf(error.getErrorCode()));
        Log.e(TAG, "Error detail: " + error.getErrorDetail());
    }

    private void showError(String message){
        showMessage(message);
        hideDialogLoading();
    }

    public void logUser(User user){
        Log.i(LOG_USER, "Id: " + String.valueOf(user.getId()));
        Log.i(LOG_USER, "FirstName: " + user.getFirstName());
        Log.i(LOG_USER, "LastName: " + user.getLastName());
        Log.i(LOG_USER, "Email: " + user.getEmail());
        Log.i(LOG_USER, "Token: " + user.getToken());
    }
}
