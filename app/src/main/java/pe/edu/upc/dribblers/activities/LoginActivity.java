package pe.edu.upc.dribblers.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.Google;

public class LoginActivity extends BaseActivity {

    CallbackManager callbackManager;
    GoogleApiClient mGoogleApiClient;
    static Context context;
    private static final int RC_SIGN_IN = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitializeGoogle();
    }
    private void InitializeGoogle(){
        mGoogleApiClient = Google.getClient(this);
        findViewById(R.id.googleBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
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
            User user = Google.SignInResult(result);
            signIn(user, true);
        }
    }
}
