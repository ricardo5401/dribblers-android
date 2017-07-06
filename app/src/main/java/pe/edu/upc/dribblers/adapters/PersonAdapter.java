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
import pe.edu.upc.dribblers.activities.EventActivity;
import pe.edu.upc.dribblers.backend.models.User;

/**
 * Created by RICHI on 6/07/2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private List<User> mUsers;

    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_people, parent, false);
        PersonAdapter.ViewHolder mViewHolder = new PersonAdapter.ViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonAdapter.ViewHolder holder, final int position) {
        String fullName = mUsers.get(position).getFirstName() + " " + mUsers.get(position).getLastName();
        holder.mPersonTextView.setText(fullName);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public List<User> getPeople() {
        return mUsers;
    }

    public void setPeople(List<User> mUsers) {
        this.mUsers = mUsers;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mPersonTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mPersonTextView = (TextView) itemView.findViewById(R.id.personTextView);
        }
    }
}
