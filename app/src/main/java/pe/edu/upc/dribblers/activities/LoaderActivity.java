package pe.edu.upc.dribblers.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import pe.edu.upc.dribblers.R;

public class LoaderActivity extends AppCompatActivity {

    static final int SPLASH_SCREEN_DELAY = 2000;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        context = this;
        //loadUser();
        simulateLoader();
    }

    private void simulateLoader(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Start the next activity
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

    private void loadUser(){
        //here put logic to load user from sugar
        goToLogin();
    }

    private void goToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
