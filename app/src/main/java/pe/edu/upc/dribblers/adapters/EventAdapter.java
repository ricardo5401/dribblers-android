package pe.edu.upc.dribblers.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.activities.TrainingPlanActivity;
import pe.edu.upc.dribblers.backend.models.Event;

/**
 * Created by RICHI on 4/07/2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private List<Event> mEvents;

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_training_plan, parent, false);
        EventAdapter.ViewHolder mViewHolder = new EventAdapter.ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mEvents.get(position).getDescription());
        holder.mShootTypeTextView.setText(mEvents.get(position).getStringDate());
        holder.mPlanAnImageView.setDefaultImageResId(R.drawable.training_placeholder);
        holder.mTrainingPlanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DribblersApp.getInstance().setCurrentEvent(mEvents.get(position));
                v.getContext().startActivity(new Intent(v.getContext(), TrainingPlanActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public List<Event> getEvents() {
        return mEvents;
    }

    public void setEvents(List<Event> mTrainingPlans) {
        this.mEvents = mTrainingPlans;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mTrainingPlanCardView;
        private TextView mNameTextView;
        private TextView mShootTypeTextView;
        private ANImageView mPlanAnImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTrainingPlanCardView = (CardView) itemView.findViewById(R.id.trainingPlanCardView);
            mNameTextView = (TextView) itemView.findViewById(R.id.planNameTextView);
            mShootTypeTextView = (TextView) itemView.findViewById(R.id.planShootTypeTextView);
            mPlanAnImageView = (ANImageView)itemView.findViewById(R.id.planAnImageView);
        }
    }
}
