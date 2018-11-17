package com.example.helloworld.myapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.PopupWindow;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.util.PopUpActivity;
import com.github.chrisbanes.photoview.PhotoView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class DailyFragment extends Fragment {
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
        ImageView helpImg = (ImageView)view.findViewById(R.id.help);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String StrDay = year+"."+(month+1)+"."+dayOfMonth;
                Toast.makeText(getContext(), StrDay, Toast.LENGTH_SHORT).show();
            }
     });

        helpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_popup, (ViewGroup) v.findViewById(R.id.popup));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(getContext());

                aDialog.setTitle("개월 수 별 먹어도 되는 음식들");
                aDialog.setView(layout);

                AlertDialog ad = aDialog.create();
                ad.show();
            }
        });

        return view;
    }
}
