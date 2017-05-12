package pe.edu.upc.dribblers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;

public class MainActivity extends BaseActivity {

    User user;
    TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }
    private void initializeComponents(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dribblers");
        welcomeTextView = (TextView)findViewById(R.id.welcomeTextView);
        user = (User)getIntent().getSerializableExtra("user");
        welcomeTextView.setText("Welcome " + user.getFirstName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                goToLogin();
                return true;
            case R.id.settings:
                return true;
        }
        return false;
    }
}
