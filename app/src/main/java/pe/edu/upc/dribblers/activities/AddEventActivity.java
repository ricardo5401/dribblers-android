package pe.edu.upc.dribblers.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.upc.dribblers.DribblersApp;
import pe.edu.upc.dribblers.R;
import pe.edu.upc.dribblers.backend.models.Event;
import pe.edu.upc.dribblers.backend.models.User;
import pe.edu.upc.dribblers.backend.network.DribblersAPI;

public class AddEventActivity extends BaseActivity {

    EditText mEditTextEventName;
    EditText mEditTextEventPlace;
    EditText mEditTextEventDate;
    ImageButton mCancelEventBTN;
    ImageButton mCreateEventBTN;
    Date mDate;
    Event mEvent;
    User mUser;
    private static String TAG = "ADD_EVENT";
    private SimpleDateFormat mFormatter =
            new SimpleDateFormat("MMMM dd yyyy hh:mm aa", java.util.Locale.forLanguageTag("EN"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Event");
        mEvent = new Event();
        mUser = DribblersApp.getInstance().getCurrentUser();
        mEditTextEventName = (EditText) findViewById(R.id.editTextEventName);
        mEditTextEventPlace = (EditText) findViewById(R.id.editTextEventPlace);
        mEditTextEventDate = (EditText) findViewById(R.id.editTextEventDate);
        mCancelEventBTN = (ImageButton) findViewById(R.id.cancelEventBTN);
        mCreateEventBTN = (ImageButton) findViewById(R.id.createEventBTN);
        mEditTextEventDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEditTextEventDate.setEnabled(false);
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        .setTheme(SlideDateTimePicker.HOLO_DARK)
                        .build()
                        .show();
                return false;
            }
        });
        mCancelEventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        mCreateEventBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    bindFields();
                    createEvent();
                }else{
                    showMessage("Please complete fields");
                }
            }
        });
    }

    private boolean isValid(){
        return !mEditTextEventDate.getText().toString().isEmpty() &&
                !mEditTextEventName.getText().toString().isEmpty() &&
                !mEditTextEventPlace.getText().toString().isEmpty();
    }

    private void bindFields(){
        mEvent.setPlace(mEditTextEventPlace.getText().toString())
                .setDescription(mEditTextEventName.getText().toString())
                .setEventDate(mDate)
                .setUserId(mUser.getForeId())
                .setPublic(true);
    }

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            mDate = date;
            mEditTextEventDate.setText(mFormatter.format(date));
            mEditTextEventDate.setEnabled(true);
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
            mEditTextEventDate.setEnabled(true);
            Toast.makeText(AddEventActivity.this,
                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                logout();
                goToLogin();
                return true;
            case R.id.settings:
                return true;
        }
        return false;
    }

    private void createEvent() {
        //new sign in via social network
        showDialogLoading("Saving event");
        Log.i(TAG, "URL: " + DribblersAPI.EVENT_URL);
        JSONObject event = mEvent.serialize();
        Log.e(TAG, event.toString());
        AndroidNetworking.post(DribblersAPI.EVENT_URL)
                .addHeaders("Authorization", mUser.getToken())
                .addJSONObjectBody(event)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, response.toString());
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onError(ANError error) {
                        manageNetworkError(error, TAG);
                        hideDialogLoading();
                        showError("Server error, try again");
                    }
                });
    }
}
