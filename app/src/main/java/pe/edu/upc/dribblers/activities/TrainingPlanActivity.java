package pe.edu.upc.dribblers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.adapters.TrainingActivityAdapter;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.DribblersAPI;

public class TrainingPlanActivity extends AppCompatActivity {

    TrainingPlan mTrainingPlan;
    List<TrainingActivity> mTrainingActivities;
    TextView mTrainingPlanTextView;
    private static String TAG = "TRAINING_PLAN_ACTIVITY";
    private RecyclerView mTrainingActivityRecyclerView;
    private TrainingActivityAdapter mTrainingActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_plan);
        mTrainingPlan = DribblersApp.getInstance().getCurrentPlan();
        mTrainingPlanTextView = (TextView) findViewById(R.id.trainingPlanTextView);
        mTrainingPlanTextView.setText(mTrainingPlan.getName());
        mTrainingActivities = new ArrayList<>();
        mTrainingActivityRecyclerView = (RecyclerView) findViewById(R.id.trainingActivityRecyclerView);
        mTrainingActivityAdapter = new TrainingActivityAdapter();
        mTrainingActivityAdapter.setTrainingActivities(mTrainingActivities);
        mTrainingActivityRecyclerView.setAdapter(mTrainingActivityAdapter);
        mTrainingActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        updatePlans();
    }

    private void updatePlans(){
        User mUser = DribblersApp.getInstance().getCurrentUser();
        AndroidNetworking.get(DribblersAPI.TRAINING_ACTIVITY_URL + "?plan_id=" + mTrainingPlan.getForeId().toString())
                .addHeaders("Authorization", mUser.getToken())
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            mTrainingActivities = TrainingActivity.build(response);
                            mTrainingActivityAdapter.setTrainingActivities(mTrainingActivities);
                            mTrainingActivityAdapter.notifyDataSetChanged();
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
