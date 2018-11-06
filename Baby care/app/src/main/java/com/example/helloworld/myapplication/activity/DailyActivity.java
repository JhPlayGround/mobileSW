package com.example.helloworld.myapplication.activity;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;

import java.util.Calendar;

public class DailyActivity extends Fragment {
    MainActivity activity;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup mainFragmentLayout, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.main_daily,mainFragmentLayout,false);

        CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);

        ImageView warningImg = (ImageView)view.findViewById(R.id.warning);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String StrDay = year+"."+(month+1)+"."+dayOfMonth;
                String food = "오늘의 영양소 탄수화물 \n 추천 재료 : 쌀 / 대채 재료 : 밀가루";
                Toast.makeText(getContext(), food, Toast.LENGTH_SHORT).show();
            }
     });
        warningImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

}
