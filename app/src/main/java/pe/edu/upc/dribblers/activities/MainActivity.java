package pe.edu.upc.dribblers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;

public class MainActivity extends AppCompatActivity {

    User user;
    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcomeTextView = (TextView)findViewById(R.id.welcomeTextView);
        user = (User)getIntent().getSerializableExtra("user");
        welcomeTextView.setText("Welcome " + user.getFirstName());
    }
}
