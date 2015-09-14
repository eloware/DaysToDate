package com.eloware.daystodate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private dateChanger myDateChangerInstance;
    private DatePicker dpTo;
    private DatePicker dpFrom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDateChangerInstance = new dateChanger();


        TextView tv = (TextView)findViewById(R.id.to_days);
        tv.setText(getString(R.string.days_to, 0));

        dpFrom = (DatePicker)findViewById(R.id.dp_from);
        dpTo = (DatePicker)findViewById(R.id.dp_to);

        dpFrom.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                myDateChangerInstance);

        dpTo.init(Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
                myDateChangerInstance);

    }

    private class dateChanger implements DatePicker.OnDateChangedListener{
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            // Toast.makeText(getApplicationContext(),"Button pressed", Toast.LENGTH_LONG).show();
            Calendar calFrom = Calendar.getInstance();
            Calendar calTo = Calendar.getInstance();

            calTo.set(dpTo.getYear(), dpTo.getMonth(), dpTo.getDayOfMonth());
            calFrom.set(dpFrom.getYear(), dpFrom.getMonth(), dpFrom.getDayOfMonth());

            if (calFrom.getTimeInMillis() > calTo.getTimeInMillis())
            {
                Toast.makeText(getApplicationContext(), R.string.from_higher_than_to, Toast.LENGTH_SHORT).show();
                return;
            }

            int days = 0;
            while(calFrom.getTimeInMillis() < calTo.getTimeInMillis())
            {
                calFrom.add(Calendar.DAY_OF_MONTH, 1);
                days++;
            }

            TextView tv = (TextView)findViewById(R.id.to_days);
            tv.setText(getString(R.string.days_to, days));
        }

    }


}
