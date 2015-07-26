package com.example.alanrogue.bluetoothleproject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class hrm_setup extends Activity {

    private static final String LogTag = "BLETAG";
    private EditText edtAgeInput;
    private DatePickerDialog datePickerDialogAge;
    private SimpleDateFormat InputDateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrm_setup);

        InputDateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.TAIWAN);

        HRM_findViewByID();
        HRM_SetEdtTouchListener();
    }

    private void HRM_SetEdtTouchListener() {
        edtAgeInput.setOnTouchListener(edtAgeOnTouchListener);

        Calendar AgeInitCalendar = Calendar.getInstance();
        datePickerDialogAge = new DatePickerDialog(this, dateSetListenerAge, AgeInitCalendar.get(Calendar.YEAR), AgeInitCalendar.get(Calendar.MONTH), AgeInitCalendar.get(Calendar.DATE));
    }

    private DatePickerDialog.OnDateSetListener dateSetListenerAge = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            int cal_user_age = 0;

            Calendar InputDate = Calendar.getInstance();
            InputDate.set(year, monthOfYear, dayOfMonth);
            Log.d(LogTag, Integer.toString(year));
            Log.d(LogTag, Integer.toString(monthOfYear));
            Log.d(LogTag, Integer.toString(dayOfMonth));

            cal_user_age = calculateUserAge(year, monthOfYear, dayOfMonth);
            edtAgeInput.setText(Integer.toString(cal_user_age));
            //edtAgeInput.setText(InputDateFormatter.format(InputDate.getTime()));
        }
    };

    private int calculateUserAge(int iYear, int iMonth, int iDay) {
        GregorianCalendar calCalendar = new GregorianCalendar();
        int cur_year, cur_month, cur_day, user_Age;

        cur_year = calCalendar.get(Calendar.YEAR);
        cur_month = calCalendar.get(Calendar.MONTH);
        cur_day = calCalendar.get(Calendar.DAY_OF_MONTH);

        calCalendar.set(iYear, iMonth, iDay);

        user_Age = cur_year - calCalendar.get(Calendar.YEAR);
        if ((cur_month < calCalendar.get(Calendar.MONTH)) || ((cur_month == calCalendar.get(Calendar.MONTH)) && cur_day < calCalendar.get(Calendar.DAY_OF_MONTH))) {
            --user_Age;
        }
        if (user_Age < 0) {
            throw new IllegalArgumentException("User Age < 0");
        }
        return user_Age;
    }

    private void HRM_findViewByID() {
        edtAgeInput = (EditText) findViewById(R.id.edtAge);
        edtAgeInput.setInputType(InputType.TYPE_NULL);
        edtAgeInput.requestFocus();

    }

    private View.OnTouchListener edtAgeOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            datePickerDialogAge.show();
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hrm_setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
