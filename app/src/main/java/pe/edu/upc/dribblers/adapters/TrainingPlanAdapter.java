package pe.edu.upc.dribblers.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;

/**
 * Created by RICHI on 19/06/2017.
 */

public class TrainingPlanAdapter extends RecyclerView.Adapter<TrainingPlanAdapter.ViewHolder> {

    private List<TrainingPlan> mTrainingPlans;

    @Override
    public TrainingPlanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_training_plan, parent, false);
        ViewHolder mViewHolder = new ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TrainingPlanAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mTrainingPlans.get(position).getName());
        holder.mShootTypeTextView.setText(mTrainingPlans.get(position).getShootType());
        holder.mPlanAnImageView.setDefaultImageResId(R.drawable.training_placeholder);
        holder.mPlanAnImageView.setImageUrl(mTrainingPlans.get(position).getPictureUrl());
        holder.mTrainingPlanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTrainingPlans.size();
    }

    public List<TrainingPlan> getSources() {
        return mTrainingPlans;
    }

    public void setSources(List<TrainingPlan> mTrainingPlans) {
        this.mTrainingPlans = mTrainingPlans;
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
