package pe.edu.upc.dribblers.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.fragments.CountDownFragment;

/**
 * Created by Richard on 20/05/2017.
 */

public class SessionTrainingActivity extends BaseActivity {

   Fragment fragment;
    User user;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronometer);
        initializeComponents();
    }



    private void initializeComponents(){
        fragmentManager = getSupportFragmentManager();
        fragment = CountDownFragment.newInstance();
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.chronometerContainerFrameLayout,fragment).commit();

    }


}
