package pe.edu.upc.dribblers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.edu.upc.dribblers.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeTrainingFragment extends Fragment {

    @BindView(R.id.freeTrainingTextView) TextView freeTrainingTextView;
    @BindView(R.id.basicTrainingTextView) TextView basicTrainingTextView;
    @BindView(R.id.advancedTrainingTextView) TextView advancedTrainingTextView;
    @BindView(R.id.titleTypeTextView) TextView titleTypeTextView;
    @BindView(R.id.buttonStartRelativeLayout) RelativeLayout buttonStartRelativeLayout;

    View homeTrainingView;

    public static HomeTrainingFragment newInstance(){
        HomeTrainingFragment fragment = new HomeTrainingFragment();
        return fragment;
    }

    public HomeTrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeTrainingView =  inflater.inflate(R.layout.fragment_home_training, container, false);
        ButterKnife.bind(this, homeTrainingView);
        changeTrainingEvent();
        return homeTrainingView;
    }

    private void changeTrainingEvent(){
        freeTrainingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanOptions();
                freeTrainingTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                titleTypeTextView.setText(getString(R.string.free_training));
            }
        });
        basicTrainingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanOptions();
                basicTrainingTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                titleTypeTextView.setText(getString(R.string.basic_training));
            }
        });
        advancedTrainingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanOptions();
                advancedTrainingTextView.setTextColor(getResources().getColor(R.color.colorBlack));
                titleTypeTextView.setText(getString(R.string.advanced_training));
            }
        });
    }

    private void cleanOptions(){
        freeTrainingTextView.setTextColor(getResources().getColor(R.color.colorGray));
        basicTrainingTextView.setTextColor(getResources().getColor(R.color.colorGray));
        advancedTrainingTextView.setTextColor(getResources().getColor(R.color.colorGray));
    }

}
