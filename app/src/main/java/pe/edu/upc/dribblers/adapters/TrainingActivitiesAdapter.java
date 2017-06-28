package pe.edu.upc.dribblers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;

/**
 * Created by herrmartell on 5/16/17.
 */

public class TrainingActivitiesAdapter extends
        RecyclerView.Adapter<TrainingActivitiesAdapter.ViewHolder> {

    private List<TrainingActivity> mTrainingActivities;

    @Override
    public TrainingActivitiesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_home_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(TrainingActivitiesAdapter.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mTrainingActivities.size();
    }

    public List<TrainingActivity> getTrainingActivitiesItems() { return mTrainingActivities; }

    public void setTrainingActivitiesItems(List<TrainingActivity> trainingActivitiesDays) {
        this.mTrainingActivities = trainingActivitiesDays;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView trainingNameTextView;
        TextView scheduleTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            trainingNameTextView = (TextView) itemView.findViewById(R.id.trainingNameTextView);
            scheduleTextView = (TextView) itemView.findViewById(R.id.scheduleTextView);
        }
    }

}
