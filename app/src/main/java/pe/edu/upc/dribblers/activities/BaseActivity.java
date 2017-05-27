package pe.edu.upc.dribblers.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.Constants;

/**
 * Created by ricardo on 5/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String SIGNIN_TAG = "SIGNIN_USER";
    private static final String LOG_USER = "LOG_USER";
    Handler toastMessage;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        createHandlers(this);
    }

    protected void createHandlers(final Context context) {
        toastMessage = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message message) {
                Toast.makeText(context, message.obj.toString(), Toast.LENGTH_LONG).show();
            }
        };
    }

    protected void showMessage(String mensaje) {
        toastMessage.obtainMessage(1, mensaje).sendToTarget();
    }

    protected void showDialogLoading(String mensaje) {
        this.dialog.setMessage(mensaje);
        this.dialog.show();
    }

    protected void hideDialogLoading() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    protected void signIn(final User user, final boolean redirect) {
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
                        User authenticatedUser = User.build(extractUser(response));
                        if (authenticatedUser != null && !authenticatedUser.getEmail().isEmpty()) {
                            Log.i(SIGNIN_TAG, "Sign in successfully");
                            logUser(authenticatedUser);
                            saveUser(authenticatedUser);
                            if (redirect) {
                                goToMain(authenticatedUser);
                            }
                        } else {
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

    public void goToMain(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", user);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private JSONObject extractUser(JSONObject jsonObject) {
        try {
            return jsonObject.getJSONObject("user");
        } catch (JSONException e) {
            e.printStackTrace();
            return jsonObject;
        }
    }

    private void manageNetworkError(ANError error, String TAG) {
        Log.e(TAG, "Error code: " + String.valueOf(error.getErrorCode()));
        Log.e(TAG, "Error detail: " + error.getErrorDetail());
    }

    public void showError(String message) {
        showMessage(message);
        hideDialogLoading();
    }

    public void logUser(User user) {
        Log.i(LOG_USER, "Id: " + String.valueOf(user.getForeId()));
        Log.i(LOG_USER, "FirstName: " + user.getFirstName());
        Log.i(LOG_USER, "LastName: " + user.getLastName());
        Log.i(LOG_USER, "Email: " + user.getEmail());
        Log.i(LOG_USER, "Token: " + user.getToken());
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        removerSavedEmail();
        goToLogin();
    }

    public void saveUser(User user) {
        User fromStorage = User.findByEmail(user.getEmail());
        if (fromStorage == null) {
            user.save();
        }
        storeEmail(user.getEmail());
    }

    public void storeEmail(String email) {
        SharedPreferences.Editor sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        sharedPreferences.putString("email", email);
        sharedPreferences.commit();
        Log.i("STORAGE_USER", "Saved!");
    }

    public String loadEmail() {
        Log.i("STORAGE_USER", "loaded!");
        return getSharedPreferences("prefs", MODE_PRIVATE).getString("email", null);
    }

    public void removerSavedEmail() {
        getSharedPreferences("prefs", MODE_PRIVATE).edit().remove("email").apply();
        Log.i("STORAGE_USER", "Removed!");
    }
}
