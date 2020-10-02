package com.mobile.pakaryowidhet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gamatechno.pakaryo.widget.materialcalendar.CalendarView;
import com.gamatechno.pakaryo.widget.materialcalendar.EventDay;
import com.gamatechno.pakaryo.widget.materialcalendar.listeners.OnDayClickListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    Button btn_start, btn_finish;
    boolean isStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = findViewById(R.id.btn_start);
        btn_finish = findViewById(R.id.btn_finish);
        calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.set(eventDay.getCalendar().get(Calendar.YEAR), (eventDay.getCalendar().get(Calendar.MONTH)), eventDay.getCalendar().get(Calendar.DAY_OF_MONTH));
                } catch (Exception e){

                }
                if(isStart){
                    calendarView.addEventStart(new EventDay(calendar, getResources().getDrawable(R.drawable.calendarview_color_circle_today), getResources().getColor(R.color.orange_400), EventDay.TYPE_START), getResources().getDrawable(R.drawable.calendarview_color_circle_today), getResources().getColor(R.color.orange_400));
                } else {
                    calendarView.addEventFinish(MainActivity.this, new EventDay(calendar, getResources().getDrawable(R.drawable.calendarview_color_circle_today), getResources().getColor(R.color.orange_400), EventDay.TYPE_FINISH), getResources().getDrawable(R.drawable.calendarview_color_circle_today), getResources().getColor(R.color.orange_400));
                }
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart = true;
            }
        });
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isStart = false;
            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 8, 29);
        calendarView.addEventToday(new EventDay(calendar, getResources().getDrawable(R.drawable.calendarview_color_circle_today), getResources().getColor(R.color.orange_400), true));
    }
}