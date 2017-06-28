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
import pe.edu.upc.dribblers.backend.models.TrainingActivity;

/**
 * Created by alumnos on 6/22/17.
 */

public class TrainingActivityAdapter extends RecyclerView.Adapter<TrainingActivityAdapter.ViewHolder> {

    private List<TrainingActivity> mTrainingActivities;

    @Override
    public TrainingActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_training_plan, parent, false);
        TrainingActivityAdapter.ViewHolder mViewHolder = new TrainingActivityAdapter.ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(TrainingActivityAdapter.ViewHolder holder, final int position) {
        holder.mNameTextView.setText(mTrainingActivities.get(position).getName());
        holder.mShootTypeTextView.setText(mTrainingActivities.get(position).getDescription());
        holder.mPlanAnImageView.setDefaultImageResId(R.drawable.training_placeholder);
        holder.mPlanAnImageView.setImageUrl(mTrainingActivities.get(position).getPictureUrl());
        holder.mTrainingPlanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTrainingActivities.size();
    }

    public List<TrainingActivity> getTrainingActivities() {
        return mTrainingActivities;
    }

    public void setTrainingActivities(List<TrainingActivity> mTrainingActivities) {
        this.mTrainingActivities = mTrainingActivities;
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
