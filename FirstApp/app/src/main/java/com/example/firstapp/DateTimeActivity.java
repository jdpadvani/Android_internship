package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class DateTimeActivity extends AppCompatActivity
{
    Button btnDate,btnTime;
    TextView txtDate,txtTime;

    // Date
    private Calendar calendar;
    private int year, month, day;

    // Time
    private String format = "";
    int hour ;
    int min ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        btnDate=findViewById(R.id.btnDate);
        btnTime=findViewById(R.id.btnTime);

        txtDate=findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // Time
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        showDate(year, month+1, day);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setDate();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setTime();
            }
        });
    }

    public void setDate() {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999)
        {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        else {
            return new TimePickerDialog(this,myTimeListner,hour,min,false);
        }
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private TimePickerDialog.OnTimeSetListener myTimeListner =new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            showTime(hourOfDay,minute);
        }
    };

    private void showDate(int year, int month, int day) {
        txtDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void setTime()
    {
        showTime(hour, min);
        showDialog(99);
    }

    public void showTime(int hour, int min)
    {
        if (hour == 0)
        {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        txtTime.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }
}