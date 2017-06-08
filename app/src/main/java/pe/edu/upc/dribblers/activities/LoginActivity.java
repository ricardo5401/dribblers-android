package pe.edu.upc.dribblers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
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
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.DribblersAPI;
import pe.edu.upc.dribblers.backend.network.Facebook;
import pe.edu.upc.dribblers.backend.network.Google;
import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 20;
    CallbackManager mCallbackManager;
    GoogleApiClient mGoogleApiClient;
    LoginButton mFacebookBTN;
    SignInButton mGoogleBTN;
    GifImageView mLoadingGiftIV;
    TextView mLoadingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeComponents();
    }

    private void initializeComponents() {
        removerSavedEmail();
        mCallbackManager = CallbackManager.Factory.create();
        mLoadingTextView = (TextView) findViewById(R.id.loadingGiftText);
        mLoadingGiftIV = (GifImageView) findViewById(R.id.loadingGift);
        initializeGoogleAuth();
        initializeFacebookAuth();
    }

    private void initializeGoogleAuth() {
        mGoogleBTN = (SignInButton) findViewById(R.id.googleBTN);
        TextView textView = (TextView) mGoogleBTN.getChildAt(0);
        textView.setText("Continuar con Google");
        textView.setTextAlignment(ViewFlipper.TEXT_ALIGNMENT_TEXT_START);
        mGoogleApiClient = Google.getClient(this);
        mGoogleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void initializeFacebookAuth() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFacebookBTN = (LoginButton) findViewById(R.id.facebookBTN);
        mFacebookBTN.setReadPermissions(Facebook.ReadPermission());
        mFacebookBTN.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                try {
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    Log.v("FACEBOOK_AUTH", response.toString());
                                    handleSignInResult(Facebook.SignInResult(object));
                                }
                            });
                    ;
                    request.setParameters(Facebook.Parameters());
                    request.executeAsync();
                } catch (Exception ex) {
                    showMessage(ex.getMessage());
                    handleSignInResult(null);
                }
            }

            @Override
            public void onCancel() {
                Log.e("FACEBOOK_AUTH", "cancelado");
                showMessage("Login cancelado");
            }

            @Override
            public void onError(FacebookException exception) {
                showMessage(exception.getMessage());
                exception.printStackTrace();
            }

        });
    }

    private void showLogin(){
        mLoadingGiftIV.setVisibility(View.INVISIBLE);
        mLoadingTextView.setVisibility(View.INVISIBLE);
        mGoogleBTN.setVisibility(View.VISIBLE);
        mFacebookBTN.setVisibility(View.VISIBLE);
    }

    private void showLoading(){
        mLoadingGiftIV.setVisibility(View.VISIBLE);
        mLoadingTextView.setVisibility(View.VISIBLE);
        mGoogleBTN.setVisibility(View.INVISIBLE);
        mFacebookBTN.setVisibility(View.INVISIBLE);
    }


    private void setLoadingText(String text){
        mLoadingTextView.setText(text);
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
            handleSignInResult(Google.SignInResult(result));
        } else { //facebook callback
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void handleSignInResult(User user) {
        if (user != null && !user.getEmail().isEmpty()) {
            signIn(user, true);
        } else {
            showMessage("Unknown error, please try again");
        }
    }
    private void signIn(final User user, final boolean redirect) {
        //new sign in via social network
        setLoadingText("Iniciando ...");
        showLoading();
        Log.i(SIGNIN_TAG, "URL: " + DribblersAPI.AUTHORIZE_URL);
        AndroidNetworking.post(DribblersAPI.AUTHORIZE_URL)
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
                            showLogin();
                            Log.e(SIGNIN_TAG, "Error on signin");
                            showError("Server error, try again");
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        showLogin();
                        manageNetworkError(error, SIGNIN_TAG);
                        showError("Server error, try again");
                    }
                });
    }
}
