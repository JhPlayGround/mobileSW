package com.example.helloworld.myapplication.activity;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.helloworld.myapplication.R;

import java.util.Calendar;

public class DailyActivity extends Fragment {
    MainActivity activity;
    private PopupWindow mPopupWindow;

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

        /*
        warningImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
           }
        });*/
        warningImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popupView = getLayoutInflater().inflate(R.layout.activity_popup, null);
                mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //popupView 에서 (LinearLayout 을 사용) 레이아웃이 둘러싸고 있는 컨텐츠의 크기 만큼 팝업 크기를 지정
                mPopupWindow.setFocusable(true); // 외부 영역 선택히 PopUp 종료
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            }
        });

        return view;
    }

}
