package pe.edu.upc.dribblers.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.TrainingActivity;

/**
 * Created by usuario on 18/05/2017.
 */

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.ViewHolder>  {

    List<TrainingActivity> trainingActivityList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(TrainingActivity trainingActivity);
    }

    public TrainingAdapter(List<TrainingActivity> trainingActivityList, OnItemClickListener onItemClickListener) {
        this.trainingActivityList = trainingActivityList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_training_item, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TrainingActivity trainingActivity = trainingActivityList.get(position);
        holder.itemTrainingCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(trainingActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trainingActivityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itemTrainingCardView) CardView itemTrainingCardView;
        @BindView(R.id.dateTextView) TextView dateTextView;
        @BindView(R.id.titleTextView) TextView titleTextView;
        @BindView(R.id.assertsTextView) TextView assertsTextView;
        @BindView(R.id.failsTextView) TextView failsTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
