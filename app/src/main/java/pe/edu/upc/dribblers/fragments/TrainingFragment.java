package pe.edu.upc.dribblers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.adapters.TrainingAdapter;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrainingFragment extends Fragment {

    @BindView(R.id.trainingRecyclerView)
    RecyclerView mTrainingRecyclerView;

    List<TrainingActivity> mActivities;
    TrainingAdapter mTrainingAdapter;
    RecyclerView.LayoutManager mTrainingLayoutManager;

    View trainingView;

    public static TrainingFragment newInstance(){
        TrainingFragment fragment = new TrainingFragment();
        return fragment;
    }

    public TrainingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        trainingView = inflater.inflate(R.layout.fragment_training, container, false);
        ButterKnife.bind(this,trainingView);
        setAdapter();
        return trainingView;
    }

    private void setAdapter(){
        mActivities = new ArrayList<>();
        mTrainingLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        TrainingAdapter.OnItemClickListener mDetailTraining = new TrainingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TrainingActivity trainingActivity) {
                // ABRIR EL DETALLE DE ENTRENAMIENTO
            }
        };
        mTrainingAdapter = new TrainingAdapter(mActivities,mDetailTraining);
        mTrainingRecyclerView.setLayoutManager(mTrainingLayoutManager);
        mTrainingRecyclerView.setAdapter(mTrainingAdapter);

        LoadActivities();
    }

    private void LoadActivities(){
        TrainingActivity trainingActivity;
        for(int i = 0 ; i < 10; i++){
            trainingActivity = new TrainingActivity();
            mActivities.add(trainingActivity);
        }
        mTrainingAdapter.notifyDataSetChanged();
    }

}
