package pe.edu.upc.dribblers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.adapters.PersonAdapter;
import pe.edu.upc.dribblers.backend.models.Event;
import pe.edu.upc.dribblers.backend.models.User;

public class EventActivity extends AppCompatActivity {

    Button mJoinBTN;
    Event mEvent;
    TextView mTextViewEventDescription;
    TextView mTextViewEventPlace;
    TextView mTextViewEventDate;
    PersonAdapter mPersonAdapter;
    RecyclerView mEventRecyclerView;
    List<User> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mEvent = DribblersApp.getInstance().getCurrentEvent();
        mJoinBTN = (Button) findViewById(R.id.joinBTN);
        mTextViewEventDescription = (TextView) findViewById(R.id.textViewEventDescription);
        mTextViewEventPlace = (TextView) findViewById(R.id.textViewEventPlace);
        mTextViewEventDate = (TextView) findViewById(R.id.textViewEventDate);
        mTextViewEventDescription.setText(mEvent.getDescription());
        mTextViewEventPlace.setText(mEvent.getmPlace());
        mTextViewEventDate.setText(mEvent.getStringDate());
        mEventRecyclerView = (RecyclerView) findViewById(R.id.eventRecyclerView);
        mPersonAdapter = new PersonAdapter();
        mUsers = new ArrayList<>();
        mPersonAdapter.setPeople(mUsers);
        mEventRecyclerView.setAdapter(mPersonAdapter);
        mJoinBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Joining", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
