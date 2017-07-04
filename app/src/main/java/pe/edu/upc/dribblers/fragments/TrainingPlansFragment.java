package pe.edu.upc.dribblers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.adapters.TrainingPlanAdapter;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.DribblersAPI;

public class TrainingPlansFragment extends Fragment {

    private List<TrainingPlan> mTrainingPlans = new ArrayList<>();
    private static String TAG = "PLAN_FRAGMENT";
    private RecyclerView mPlansRecyclerView;
    private TrainingPlanAdapter mTrainingPlanAdapter;


    public TrainingPlansFragment() {
        // Required empty public constructor
    }

    public static TrainingPlansFragment newInstance() {
        TrainingPlansFragment fragment = new TrainingPlansFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_training_plans, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlansRecyclerView = (RecyclerView) view.findViewById(R.id.trainingPlanRecyclerView);
        mTrainingPlanAdapter = new TrainingPlanAdapter();
        mTrainingPlanAdapter.setTrainingPlans(mTrainingPlans);
        mPlansRecyclerView.setAdapter(mTrainingPlanAdapter);
        mPlansRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        updatePlans();
    }

    private void updatePlans(){
        User mUser = DribblersApp.getInstance().getCurrentUser();
        AndroidNetworking.get(DribblersAPI.TRAINING_PLAN_URL)
                .addHeaders("Authorization", mUser.getToken())
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            mTrainingPlans = TrainingPlan.build(response);
                            mTrainingPlanAdapter.setTrainingPlans(mTrainingPlans);
                            mTrainingPlanAdapter.notifyDataSetChanged();
                        }catch (Exception ex){
                            Log.e(TAG, "Error on load TrainingPlans: " + ex.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getErrorBody());
                    }
                });
    }

}
