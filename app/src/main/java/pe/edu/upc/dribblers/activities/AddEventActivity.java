package pe.edu.upc.dribblers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import pe.edu.upc.dribblers.R;

public class AddEventActivity extends AppCompatActivity {

    EditText mEditTextEventName;
    EditText mEditTextEventPlace;
    EditText mEditTextEventDate;
    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        mEditTextEventName = (EditText) findViewById(R.id.editTextEventName);
        mEditTextEventPlace = (EditText) findViewById(R.id.editTextEventPlace);
        mEditTextEventDate = (EditText) findViewById(R.id.editTextEventDate);
        mEditTextEventDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mEditTextEventDate.setEnabled(false);
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        .build()
                        .show();
                return false;
            }
        });
    }

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
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
}
