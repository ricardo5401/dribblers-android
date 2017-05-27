package pe.edu.upc.dribblers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.adapters.HomeAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeEventsFragment extends Fragment {

    RecyclerView mHomeRecyclerView;
    HomeAdapter mHomeAdapter;

    public static HomeEventsFragment newInstance(){
        HomeEventsFragment fragment = new HomeEventsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        mHomeAdapter = new HomeAdapter();
        mHomeAdapter.setTrainingActivitiesDays(DribblersApp.getInstance().getTrainingActivities());
        mHomeRecyclerView.setAdapter(mHomeAdapter);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }



    private void updateData() {
        ((HomeAdapter)
            mHomeRecyclerView.getAdapter())
                .setTrainingActivitiesDays(DribblersApp.getInstance().getTrainingActivities());
    }

}
