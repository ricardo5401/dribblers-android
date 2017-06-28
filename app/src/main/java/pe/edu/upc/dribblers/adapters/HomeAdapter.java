package pe.edu.upc.dribblers.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;

import static java.security.AccessController.getContext;

/**
 * Created by herrmartell on 5/16/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<TrainingActivity> mTrainingActivities;
    private TrainingActivitiesAdapter mTrainingActivitiesAdapter;

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_home, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.planDateTextView.setText(getTrainingActivityDate(position));
        holder.planMonthTextView.setText(getTrainingActivityMonth(position));
        mTrainingActivitiesAdapter = new TrainingActivitiesAdapter();
        mTrainingActivitiesAdapter.setTrainingActivitiesItems(
                DribblersApp.getInstance().getTrainingActivities());
        holder.cardItemsRecyclerView.setAdapter(mTrainingActivitiesAdapter);
        holder.cardItemsRecyclerView.setLayoutManager(
                new LinearLayoutManager(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return mTrainingActivities.size();
    }

    public List<TrainingActivity> getTrainingActivityDays() { return mTrainingActivities; }

    public void setTrainingActivitiesDays(List<TrainingActivity> trainingActivitiesDays) {
        this.mTrainingActivities = trainingActivitiesDays;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView planDateTextView;
        TextView planMonthTextView;
        RecyclerView cardItemsRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            planDateTextView = (TextView) itemView.findViewById(R.id.planDateTextView);
            planMonthTextView = (TextView) itemView.findViewById(R.id.planMonthTextView);
            cardItemsRecyclerView = (RecyclerView) itemView.findViewById(R.id.cardItemsRecyclerView);
        }
    }

    private String getTrainingActivityDate(int position) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd", Locale.US);
        return dateFormat.format(date);
    }

    private String getTrainingActivityMonth(int position) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MMM", Locale.US);
        return dateFormat.format(date);
    }

}
