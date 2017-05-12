package pe.edu.upc.dribblers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.Facebook;
import pe.edu.upc.dribblers.backend.network.Google;

public class LoginActivity extends BaseActivity {

    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    LoginButton facebookBTN;
    private static final int RC_SIGN_IN = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
    }
    private void initializeComponents(){
        callbackManager = CallbackManager.Factory.create();
        initializeGoogleAuth();
        initializeFacebookAuth();
    }
    private void initializeGoogleAuth(){
        mGoogleApiClient = Google.getClient(this);
        findViewById(R.id.googleBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void initializeFacebookAuth(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        facebookBTN = (LoginButton) findViewById(R.id.facebookBTN);
        facebookBTN.setReadPermissions(Facebook.ReadPermission());
        facebookBTN.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                try {
                    GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("FACEBOOK_AUTH", response.toString());
                                handleSignInResult( Facebook.SignInResult(object) );
                            }
                        });;
                    request.setParameters(Facebook.Parameters());
                    request.executeAsync();
                }catch(Exception ex){
                    showMessage(ex.getMessage());
                    handleSignInResult(null);
                }
            }

            @Override
            public void onCancel() {
                Log.e("FACEBOOK_AUTH","cancelado");
                showMessage("Login cancelado");
            }

            @Override
            public void onError(FacebookException exception) {
                showMessage(exception.getMessage());
                exception.printStackTrace();
            }

        });
    }

    @Override
    public void onBackPressed() {
        //empty prevent back to main activity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult( Google.SignInResult(result) );
        }else{ //facebook callback
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void handleSignInResult(User user){
        if(user != null && !user.getEmail().isEmpty()){
            signIn(user, true);
        }else{
            showMessage("Unknown error, please try again");
        }
    }
}
