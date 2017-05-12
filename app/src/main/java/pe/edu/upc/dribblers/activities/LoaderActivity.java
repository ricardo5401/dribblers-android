package pe.edu.upc.dribblers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;

public class LoaderActivity extends BaseActivity {

    static final int SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        simulateLoader();
    }

    private void simulateLoader(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //loading email from storage
                String email = loadEmail();
                if(email == null){
                    goToLogin();
                }else{
                    goToNextActivity(email);
                }
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    private void goToNextActivity(String email){
        User user = User.findByEmail(email);
        if(user != null){
            goToMain(user);
        }else{
            goToLogin();
        }
    }
}
