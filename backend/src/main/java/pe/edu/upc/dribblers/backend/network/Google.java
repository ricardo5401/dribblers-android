package pe.edu.upc.dribblers.backend.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import pe.edu.upc.dribblers.backend.models.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ricardo on 5/11/17.
 */

public class Google {
    private static GoogleSignInOptions getOptions(){
        return new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(Constants.Google.CLIENT_ID)
                .requestServerAuthCode(Constants.Google.CLIENT_ID, true)
                .requestEmail().build();
    }

    public static GoogleApiClient getClient(Context activity){

        return new GoogleApiClient.Builder(activity)
                .enableAutoManage((FragmentActivity) activity /* FragmentActivity */,
                        new GoogleApiClient.OnConnectionFailedListener() {
                            @Override
                            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                                Log.e("GOOGLE_AUTH","CONEXION FALLIDA");
                            }
                        } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, getOptions())
                .build();
    }
    public static User SignInResult(GoogleSignInResult result){

        Log.d("GOOGLE_AUTH", "Google auth result: " + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acc = result.getSignInAccount();

            LogResult(acc);
            return new User()
                    .setEmail(acc.getEmail())
                    .setFirstName(acc.getGivenName())
                    .setLastName(acc.getFamilyName())
                    .setToken(acc.getIdToken());
        } else {
            return null;
        }
    }

    private static void LogResult(GoogleSignInAccount acc){
        String TAG = "GOOGLE_AUTH";
        Log.e(TAG, "email= "+ acc.getEmail());
        Log.e(TAG, "family name = "+ acc.getFamilyName());
        Log.e(TAG, "display name = "+ acc.getDisplayName());
        Log.e(TAG, "given name= "+ acc.getGivenName());
        Log.e(TAG, "token = "+ acc.getIdToken());
        Log.e(TAG, "server code = "+ acc.getServerAuthCode());
    }
}
