package com.mobile.pakaryowidhet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gamatechno.pakaryo.widget.materialcalendar.CalendarView;
import com.gamatechno.pakaryo.widget.materialcalendar.EventDay;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 8, 29);
        calendarView.addEventToday(new EventDay(calendar, getResources().getDrawable(R.drawable.calendarview_color_circle_today), getResources().getColor(R.color.orange_400), true));
    }
}