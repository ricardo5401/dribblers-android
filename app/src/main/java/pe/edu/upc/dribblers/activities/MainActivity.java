package pe.edu.upc.dribblers.activities;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.fragments.EstatisticFragment;
import pe.edu.upc.dribblers.fragments.HomeFragment;
import pe.edu.upc.dribblers.fragments.NotificationFragment;
import pe.edu.upc.dribblers.fragments.TrainingFragment;

public class MainActivity extends BaseActivity {

    User user;
    BottomBar bottomBar;
    Fragment fragment;
    FragmentManager fragmentManager;
    String title = "";
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
        user = (User)getIntent().getSerializableExtra("user");

        fragmentManager = getSupportFragmentManager();
        fragment = new HomeFragment();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentContainer, fragment).commit();

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.homeMenu:
                        fragment = new HomeFragment();
                        title = "Inicio";
                        break;
                    case R.id.trainingMenu:
                        fragment = new TrainingFragment();
                        title = "Entrenamiento";
                        break;
                    case R.id.notificationMenu:
                        fragment = new NotificationFragment();
                        title = "Notificaciones";
                        break;
                    case R.id.estatiticMenu:
                        fragment = new EstatisticFragment();
                        title = "Estadisticas";
                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.contentContainer, fragment).commit();
                getSupportActionBar().setTitle(title);
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

    public User getUser(){
        return user;
    }



}
