package pe.edu.upc.dribblers.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.concurrent.TimeUnit;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.activities.SessionTrainingActivity;

/**
 * Created by Richard on 20/05/2017.
 */

public class ChronometerFragment extends Fragment implements View.OnClickListener {
    TextView mChronometerTextView;
    ImageButton mSuccesImageButton;
    ImageButton mFailImageButton;
    TextView mSuccesTextView;
    TextView mFailTextView;
    Button mStopButton;
    private long auxTimeSession;
    private  int succesCount;
    private int failCount;
    private CountDownTimer mCountDownTimer;
    private long timeSession;
    private MaterialDialog mMaterialDialog;
    public static ChronometerFragment newInstance() {
        ChronometerFragment fragment = new ChronometerFragment();
        return fragment;

    }

    public ChronometerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chronometer, container, false);
        initializeComponents(v);
        startCountDown(timeSession);

        return v;
    }

    private void initializeComponents(View v) {
        mChronometerTextView = (TextView) v.findViewById(R.id.idChronometer);
        mSuccesImageButton = (ImageButton) v.findViewById(R.id.succesImageButton);
        mFailImageButton = (ImageButton) v.findViewById(R.id.failImageButton);
        mSuccesTextView = (TextView) v.findViewById(R.id.idSuccesTextView);
        mFailTextView = (TextView) v.findViewById(R.id.idFailTextView);
        mStopButton = (Button) v.findViewById(R.id.idStopButton);
        mSuccesImageButton.setOnClickListener(this);
        mFailImageButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);
        timeSession = 30000;
        succesCount =0;
        failCount =0;
    }
    private void startCountDown(long timeSession){

        mCountDownTimer =new CountDownTimer(timeSession, 1000) { // adjust the milli seconds here
            public void onTick(long millisUntilFinished) {
                auxTimeSession = millisUntilFinished;
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                mChronometerTextView.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }
            public void onFinish() {
                //Send to Activity details

            }
        }.start();
    }

   private void buildAlertDialog(){

       mCountDownTimer.cancel();
       mMaterialDialog = new MaterialDialog.Builder(getActivity())
               .content(R.string.chronometer_dialog_alert_context)
               .positiveText(R.string.chronometer_dialog_alert_confirmation)
               .negativeText(R.string.chronometer_dialog_alert_denegate)
               .onPositive(new MaterialDialog.SingleButtonCallback() {
                   @Override
                   public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                   }
               })
               .onNegative(new MaterialDialog.SingleButtonCallback() {
                   @Override
                   public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                      resumeCountDown(auxTimeSession);
                   }
               })
               .show();


   }
   private void resumeCountDown(long auxTimeSession){
       startCountDown(auxTimeSession);
   }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.succesImageButton: {
                succesCount++;
                mSuccesTextView.setText(Integer.toString(succesCount));break;}
            case R.id.failImageButton: {
                failCount++;
                mFailTextView.setText(Integer.toString(failCount));
                break;
            }
            case R.id.idStopButton: {
                buildAlertDialog();break;
            }
        }
    }
}
