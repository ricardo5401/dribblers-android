package pe.edu.upc.dribblers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.fragments.HomeFragment;
import pe.edu.upc.dribblers.fragments.NotificationsFragment;
import pe.edu.upc.dribblers.fragments.TrainingFragment;

public class MainActivity extends BaseActivity {

    User user;
    Fragment fragment;
    FragmentManager fragmentManager;
    String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
    }

    private void initializeComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dribblers");
        user = (User) getIntent().getSerializableExtra("user");

        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mainContainerFrameLayout, fragment).commit();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.mainBottomVavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                fragment = new HomeFragment();
                                title = "Home";
                                break;
                            case R.id.action_training:
                                fragment = new TrainingFragment();
                                title = "Training";
                                break;
                            case R.id.action_notification:
                                fragment = new NotificationsFragment();
                                title = "Notifications";
                                break;
                        }
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.mainContainerFrameLayout, fragment).commit();
                        getSupportActionBar().setTitle(title);
                        return true;
                    }
                });
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

    public User getUser() {
        return user;
    }


}
