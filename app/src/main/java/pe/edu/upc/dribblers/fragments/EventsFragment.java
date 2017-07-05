package pe.edu.upc.dribblers.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import pe.edu.upc.dribblers.activities.AddEventActivity;
import pe.edu.upc.dribblers.adapters.EventAdapter;
import pe.edu.upc.dribblers.adapters.TrainingPlanAdapter;
import pe.edu.upc.dribblers.backend.models.Activity;
import pe.edu.upc.dribblers.backend.models.Event;
import pe.edu.upc.dribblers.backend.models.TrainingPlan;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.DribblersAPI;

public class EventsFragment extends Fragment {

    private List<Event> mEvents = new ArrayList<>();
    private static String TAG = "EVENT_FRAGMENT";
    private RecyclerView mEventsRecyclerView;
    private EventAdapter mEventAdapter;
    private FloatingActionButton mAddEvent;
    private static int _requestCode = 300;

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEventsRecyclerView = (RecyclerView) view.findViewById(R.id.eventsRecyclerView);
        mEventAdapter = new EventAdapter();
        mEventAdapter.setEvents(mEvents);
        mEventsRecyclerView.setAdapter(mEventAdapter);
        mEventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAddEvent = (FloatingActionButton) view.findViewById(R.id.addEvent);
        mAddEvent.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorOrangeDark)));
        mAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), AddEventActivity.class), _requestCode);
            }
        });
        updateEvents();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == _requestCode && resultCode == android.app.Activity.RESULT_OK) {
            updateEvents();
        }
    }

    private void updateEvents(){
        User mUser = DribblersApp.getInstance().getCurrentUser();
        AndroidNetworking.get(DribblersAPI.EVENT_URL)
                .addHeaders("Authorization", mUser.getToken())
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try{
                            mEvents = Event.build(response);
                            mEventAdapter.setEvents(mEvents);
                            mEventAdapter.notifyDataSetChanged();
                        }catch (Exception ex){
                            Log.e(TAG, "Error on load Events: " + ex.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getErrorBody());
                    }
                });
    }
}
