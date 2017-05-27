package pe.edu.upc.dribblers.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.edu.upc.dribblers.R;

/**
 * Created by Richard on 20/05/2017.
 */

public class CountDownFragment extends Fragment {
   TextView mTextView;
    private int secondsLeft = 0;
    private String time;
    public static CountDownFragment newInstance() {
        CountDownFragment fragment = new CountDownFragment();
        return fragment;
    }

    public CountDownFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_countdown, container, false);
        mTextView = (TextView) v.findViewById(R.id.idCountDownTextView);
        initialize();
        return v;
    }

    private void initialize() {
        CountDownTimer cT = new CountDownTimer(4000, 1) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) ((millisUntilFinished / 1000));
                mTextView.setText(Integer.toString(seconds));
            }

            public void onFinish() {
                Fragment mFragment = new ChronometerFragment().newInstance();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
               transaction.replace(R.id.chronometerContainerFrameLayout,mFragment).commit();
            }
        };
        cT.start();
    }
}
