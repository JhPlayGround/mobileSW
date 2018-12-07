package com.example.helloworld.myapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
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

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class DailyFragment extends Fragment {
    MainActivity activity;
    TextView tvDaily;
    TextView tvChange;
    TextView tvtoday;
    TextView tvreplace;
    public static String sbabymonth= ""; //엄마가 입력한 아기 개월 수
    int babymonth;

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
        tvDaily = (TextView)view.findViewById(R.id.tvDaily);
        tvChange = (TextView)view.findViewById(R.id.tvChange);
        tvtoday = (TextView)view.findViewById(R.id.tvtoday);
        tvreplace= (TextView)view.findViewById(R.id.tvreplace);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int inyear, int inmonth, int dayOfMonth) {
                tvtoday.setText((inmonth+1) +"월 " + dayOfMonth+"일 이유식");
                tvreplace.setText((inmonth+1) +"월 " + dayOfMonth+"일 대체 이유식");
                String sYear = sbabymonth.substring(0,2); //입력한 년
                String sMonth = sbabymonth.substring(2,4);//입력한 달

                int iyear = Integer.parseInt(sYear); //입력한 년 int
                int imonth = Integer.parseInt(sMonth); //입력한 달 int


                inyear =inyear - 2000;
                int year = inyear - iyear;
                int month = (inmonth+1) - imonth;

                babymonth = (year*12) + month;

                if(babymonth >= 0 && babymonth < 4 )
                {

                    tvDaily.setText("모유");
                    tvChange.setText("분유");
                }
                else if(babymonth ==4)
                {
                    if(dayOfMonth % 3 == 1)  //1,4,7,10,13,16,19,22,25,28,31
                    {
                        if(dayOfMonth%2 == 1)
                        {
                            tvDaily.setText("쌀");
                            tvChange.setText("찹쌀");
                        }
                        else
                        {
                            tvDaily.setText("찹쌀");
                            tvChange.setText("쌀");
                        }
                    }
                    else if(dayOfMonth % 3 == 2) //2,5,8,11,14,17,20,23,26,29
                    {
                        if(dayOfMonth == 2 || dayOfMonth == 17)
                        {
                            tvDaily.setText("감자");
                            tvChange.setText("고구마, 애호박, 단호박, 오이");
                        }
                        else if(dayOfMonth == 5 || dayOfMonth == 20)
                        {
                            tvDaily.setText("고구마");
                            tvChange.setText("감자, 애호박, 단호박, 오이");
                        }
                        else if(dayOfMonth == 8 || dayOfMonth == 23)
                        {
                            tvDaily.setText("호박");
                            tvChange.setText("고구마, 감자, 단호박, 오이");
                        }
                        else if(dayOfMonth == 11 || dayOfMonth == 26)
                        {
                            tvDaily.setText("단호박");
                            tvChange.setText("고구마, 애호박, 감자, 오이");
                        }
                        else
                        {
                            tvDaily.setText("오이");
                            tvChange.setText("고구마, 애호박, 단호박, 감자");
                        }
                    }
                    else //3,6,9,12,15,18,21,24,27,30
                    {
                        if(dayOfMonth == 3 || dayOfMonth ==12 || dayOfMonth == 21 || dayOfMonth == 30)
                        {
                            tvDaily.setText("사과");
                            tvChange.setText("배, 바나나");
                        }
                        else if(dayOfMonth == 6 || dayOfMonth == 15 || dayOfMonth == 24)
                        {
                            tvDaily.setText("배");
                            tvChange.setText("사과, 바나나");
                        }
                        else
                        {
                            tvDaily.setText("바나나");
                            tvChange.setText("배, 사과");
                        }

                    }
                }
                else if(babymonth == 5)
                {
                    if(dayOfMonth % 3 == 1)  //1,4,7,10,13,16,19,22,25,28,31
                    {
                        if(dayOfMonth%2 == 1)
                        {
                            tvDaily.setText("쌀");
                            tvChange.setText("찹쌀");
                        }
                        else
                        {
                            tvDaily.setText("찹쌀");
                            tvChange.setText("쌀");
                        }
                    }
                    else if(dayOfMonth % 3 == 2) //2,5,8,11,14,17,20,23,26,29
                    {
                        if(dayOfMonth == 2 || dayOfMonth == 14 || dayOfMonth == 26)
                        {
                            tvDaily.setText("무");
                            tvChange.setText("감자, 고구마, 애호박, 단호박\n오이, 브로콜리, 콜리플라워, 적양배추");
                        }
                        else if(dayOfMonth == 5 || dayOfMonth == 17 || dayOfMonth == 29)
                        {
                            tvDaily.setText("브로콜리");
                            tvChange.setText("감자, 고구마, 애호박, 단호박\n 오이, 무, 콜리플라워, 적양배추");
                        }
                        else if(dayOfMonth == 8 || dayOfMonth == 20)
                        {
                            tvDaily.setText("콜리플라워");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 적양배추");
                        }
                        else
                        {
                            tvDaily.setText("적양배추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워");
                        }
                    }
                    else //3,6,9,12,15,18,21,24,27,30
                    {
                        if(dayOfMonth %6 != 0)
                        {
                            tvDaily.setText("자두");
                            tvChange.setText("사과,배,바나나,수박");
                        }
                        else
                        {
                            tvDaily.setText("수박");
                            tvChange.setText("사과,배,바나나,자두");
                        }
                    }
                }
                else if(babymonth == 6)
                {
                    if(dayOfMonth % 6 == 1)// 1,7,13,19,25,31
                    {
                        if(dayOfMonth == 1 || dayOfMonth == 31)
                        {
                            tvDaily.setText("차조");
                            tvChange.setText("쌀, 찹쌀, 현미, 보리, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 7)
                        {
                            tvDaily.setText("현미");
                            tvChange.setText("쌀, 찹쌀, 차조, 보리, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 13)
                        {
                            tvDaily.setText("보리");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 19)
                        {
                            tvDaily.setText("수수");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 보리, 옥수수");
                        }
                        else if(dayOfMonth == 25)
                        {
                            tvDaily.setText("옥수수");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 보리, 수수");
                        }
                    }
                    else if(dayOfMonth % 6 == 2) //2,8,14,20,26
                    {
                        tvDaily.setText("소고기 안심");
                        tvChange.setText("전날 먹은 이유식");
                    }
                    else if(dayOfMonth % 6 == 3) //3,9,15,21,27
                    {
                        if(dayOfMonth == 3 || dayOfMonth == 21)
                        {
                            tvDaily.setText("가자미");
                            tvChange.setText("임연수어, 마른멸치");
                        }
                        else if(dayOfMonth == 9 || dayOfMonth ==27)
                        {
                            tvDaily.setText("임연수어");
                            tvChange.setText("가자미, 마른멸치");
                        }
                        else
                        {
                            tvDaily.setText("마른멸치");
                            tvChange.setText("가자미, 임연수어");
                        }
                    }
                    else if(dayOfMonth % 6 == 4) //4,10,16,22,28
                    {
                        if(dayOfMonth %4 == 0)
                        {
                            tvDaily.setText("김");
                            tvChange.setText("다시마");
                        }
                        else
                        {
                            tvDaily.setText("다시마");
                            tvChange.setText("김");
                        }

                    }
                    else if(dayOfMonth % 6 == 5)  //5,11,17,23,29
                    {
                        if(dayOfMonth == 5)
                        {
                            tvDaily.setText("참깨 or 검은깨");
                            tvChange.setText("밤, 대두, 검은콩, 완두콩, 강낭콩");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("밤");
                            tvChange.setText("참깨, 검은깨, 대두, 검은콩, 완두콩, 강낭콩");
                        }
                        else
                        {
                            tvDaily.setText("대두 or 검은콩 or 완두콩 or강낭콩");
                            tvChange.setText("참깨, 검은깨, 밤");
                        }
                    }
                    else  //6,12,18,24,30
                    {
                        if (dayOfMonth == 6)
                        {
                            tvDaily.setText("당근");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯");

                        }
                        else if (dayOfMonth == 12)
                        {
                            tvDaily.setText("시금치");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯");
                        }
                        else if (dayOfMonth == 18)
                        {
                            tvDaily.setText("대추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯");
                        }
                        else if (dayOfMonth == 24)
                        {
                            tvDaily.setText("배추 or 양배추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯");
                        }
                        else
                        {
                            tvDaily.setText("양송이버섯 or 새송이버섯 or 표고버섯 or 팽이버섯 or느타리버섯");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추");
                        }
                    }
                }
                else if(babymonth == 7)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth == 1)
                        {
                            tvDaily.setText("차조");
                            tvChange.setText("쌀, 찹쌀, 현미, 보리, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 10)
                        {
                            tvDaily.setText("현미");
                            tvChange.setText("쌀, 찹쌀, 차조, 보리, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 19)
                        {
                            tvDaily.setText("보리");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 수수, 옥수수");
                        }
                        else
                        {
                            tvDaily.setText("수수 or 옥수수");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 보리");
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("닭 안심");
                            tvChange.setText("닭 가슴살, 소고기 안심");
                        }
                        else
                        {
                            tvDaily.setText("닭 가슴살");
                            tvChange.setText("닭 안심, 소고기 안심");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("조기");
                            tvChange.setText("대구, 가자미, 임연수어, 마른멸치");
                        }
                        else
                        {
                            tvDaily.setText("대구");
                            tvChange.setText("조기, 가자미, 임연수어, 마른멸치");
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("미역");
                            tvChange.setText("파래, 김, 다시마");
                        }
                        else
                        {
                            tvDaily.setText("파래");
                            tvChange.setText("미역, 김, 다시마");
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 노른자");
                        tvChange.setText("전날 먹인 이유식");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        tvDaily.setText("아기용 치즈");
                        tvChange.setText("전날 먹인 이유식");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            tvDaily.setText("잣");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨");
                        }
                        else if (dayOfMonth == 16)
                        {
                            tvDaily.setText("두부");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨");
                        }
                        else
                        {
                            tvDaily.setText("들깨");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부");
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        tvDaily.setText("파슬리");
                        tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯");
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("참외");
                            tvChange.setText("사과,배,바나나,자두,수박,귤");
                        }
                        else
                        {
                            tvDaily.setText("귤");
                            tvChange.setText("사과,배,바나나,자두,수박,참외");
                        }
                    }
                }
                else if(babymonth == 8)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth == 1)
                        {
                            tvDaily.setText("차조");
                            tvChange.setText("쌀, 찹쌀, 현미, 보리, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 10)
                        {
                            tvDaily.setText("현미");
                            tvChange.setText("쌀, 찹쌀, 차조, 보리, 수수, 옥수수");
                        }
                        else if(dayOfMonth == 19)
                        {
                            tvDaily.setText("보리");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 수수, 옥수수");
                        }
                        else
                        {
                            tvDaily.setText("수수 or 옥수수");
                            tvChange.setText("쌀, 찹쌀, 차조, 현미, 보리");
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("닭 안심");
                            tvChange.setText("닭 가슴살, 소고기 안심");
                        }
                        else
                        {
                            tvDaily.setText("닭 가슴살");
                            tvChange.setText("닭 안심, 소고기 안심");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("조기");
                            tvChange.setText("대구, 가자미, 임연수어, 마른멸치");
                        }
                        else
                        {
                            tvDaily.setText("대구");
                            tvChange.setText("조기, 가자미, 임연수어, 마른멸치");
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("미역");
                            tvChange.setText("파래, 김, 다시마");
                        }
                        else
                        {
                            tvDaily.setText("파래");
                            tvChange.setText("미역, 김, 다시마");
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 노른자");
                        tvChange.setText("전날 먹인 이유식");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        tvDaily.setText("플레인 요구르트");
                        tvChange.setText("아기용 치즈");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            tvDaily.setText("잣");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨");
                        }
                        else if (dayOfMonth == 16)
                        {
                            tvDaily.setText("두부");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨");
                        }
                        else
                        {
                            tvDaily.setText("들깨");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부");
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        tvDaily.setText("파슬리");
                        tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯");
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("참외");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤");
                        }
                        else
                        {
                            tvDaily.setText("귤");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 참외");
                        }
                    }
                }
                else if(babymonth == 9)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("흑미");
                            tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두");
                        }
                        else
                        {
                            tvDaily.setText("녹두");
                            tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미");
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("닭 안심");
                            tvChange.setText("닭 가슴살, 소고기 안심");
                        }
                        else
                        {
                            tvDaily.setText("닭 가슴살");
                            tvChange.setText("닭 안심, 소고기 안심");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("새우");
                            tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 굴");
                        }
                        else
                        {
                            tvDaily.setText("굴");
                            tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우");
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("미역");
                            tvChange.setText("파래, 김, 다시마");
                        }
                        else
                        {
                            tvDaily.setText("파래");
                            tvChange.setText("미역, 김, 다시마");
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 노른자");
                        tvChange.setText("전날 먹인 이유식");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        tvDaily.setText("플레인 요구르트");
                        tvChange.setText("아기용 치즈");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            tvDaily.setText("잣");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨");
                        }
                        else if (dayOfMonth == 16)
                        {
                            tvDaily.setText("두부");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨");
                        }
                        else
                        {
                            tvDaily.setText("들깨");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부");
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("숙주");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물");
                        }
                        else
                        {
                            tvDaily.setText("콩나물");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("멜론");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 건포도");
                        }
                        else
                        {
                            tvDaily.setText("건포도");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론");
                        }
                    }
                }
                else if(babymonth == 10)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("흑미");
                            tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두");
                        }
                        else
                        {
                            tvDaily.setText("녹두");
                            tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미");
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        tvDaily.setText("돼지고기 안심");
                        tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심");
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        tvDaily.setText("날치알");
                        tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴");
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("미역");
                            tvChange.setText("파래, 김, 다시마");
                        }
                        else
                        {
                            tvDaily.setText("파래");
                            tvChange.setText("미역, 김, 다시마");
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("메추리 알");
                        tvChange.setText("달걀 노른자");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        tvDaily.setText("마가린");
                        tvChange.setText("플레인 요구르트, 아기용 치즈");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            tvDaily.setText("잣");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨");
                        }
                        else if (dayOfMonth == 16)
                        {
                            tvDaily.setText("두부");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨");
                        }
                        else
                        {
                            tvDaily.setText("들깨");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부");
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("치커리");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 무순 ,가지 ");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("무순");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리 ,가지 ");
                        }
                        else
                        {
                            tvDaily.setText("가지");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리 ,무순 ");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("포도");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 살구");
                        }
                        else
                        {
                            tvDaily.setText("살구");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도");
                        }
                    }
                }
                else if(babymonth == 11)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        tvDaily.setText("밀가루");
                        tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두,흑미");
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        tvDaily.setText("돼지고기 안심");
                        tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심");
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        tvDaily.setText("오징어");
                        tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알");
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        tvDaily.setText("한천");
                        tvChange.setText("파래, 김, 다시마,미역");
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("메추리 알");
                        tvChange.setText("달걀 노른자");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        tvDaily.setText("마가린");
                        tvChange.setText("플레인 요구르트, 아기용 치즈");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        tvDaily.setText("유부");
                        tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨");
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("치커리");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 무순 ,가지 ");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("무순");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리 ,가지 ");
                        }
                        else
                        {
                            tvDaily.setText("가지");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리 ,무순 ");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("포도");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 살구");
                        }
                        else
                        {
                            tvDaily.setText("살구");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도");
                        }
                    }
                }
                else if(babymonth == 12)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        tvDaily.setText("팥");
                        tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두, 흑미, 밀가루");
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth == 2)
                        {
                            tvDaily.setText("돼지고기 등심");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 닭 날개, 닭 다리");
                        }
                        else if(dayOfMonth == 11)
                        {
                            tvDaily.setText("닭 날개");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 다리");
                        }
                        else
                        {
                            tvDaily.setText("닭 다리");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        tvDaily.setText("게");
                        tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어");
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        tvDaily.setText("한천");
                        tvChange.setText("파래, 김, 다시마,미역");
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 흰자");
                        tvChange.setText("달걀 노른자, 메추리 알");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        if(dayOfMonth == 6)
                        {
                            tvDaily.setText("액상 요구르트");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 생크림, 버터");
                        }
                        else if(dayOfMonth == 15)
                        {
                            tvDaily.setText("생크림");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 버터");
                        }
                        else
                        {
                            tvDaily.setText("버터");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림");
                        }

                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        tvDaily.setText("두유");
                        tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부");
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("파프리카");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 쑥, 냉이, 고사리, 깻잎 ");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("쑥 or 냉이 or 고사리");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 깻잎 ");
                        }
                        else
                        {
                            tvDaily.setText("깻잎");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리 ");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            tvDaily.setText("파인애플");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 복숭아, 단감");
                        }
                        else if(dayOfMonth == 18)
                        {
                            tvDaily.setText("복숭아");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 단감");
                        }
                        else
                        {
                            tvDaily.setText("단감");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아");
                        }

                    }
                }
                else if(babymonth == 13 || babymonth == 14)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        tvDaily.setText("율무");
                        tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두, 흑미, 밀가루, 팥");
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("소고기 양지머리");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 삼겹살");
                        }
                        else
                        {
                            tvDaily.setText("삼겹살");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 소고기 양지머리");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth  == 3)
                        {
                            tvDaily.setText("고등어");
                            tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 모시조개, 맛조개, 홍합, 바지락, 소라, 전복");
                        }
                        else if(dayOfMonth  == 12)
                        {
                            tvDaily.setText("모시조개 or 맛조개");
                            tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 고등어, 홍합, 바지락, 소라, 전복");
                        }
                        else if(dayOfMonth == 21)
                        {
                            tvDaily.setText("홍합 or 바지락");
                            tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 고등어, 모시조개, 맛조개, 소라, 전복");
                        }
                        else
                        {
                            tvDaily.setText("소라 or 전복");
                            tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 고등어, 모시조개, 맛조개, 홍합, 바지락");
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        tvDaily.setText("한천");
                        tvChange.setText("파래, 김, 다시마,미역");
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 흰자");
                        tvChange.setText("달걀 노른자, 메추리 알");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("생우유");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림, 버터");
                        }
                        else
                        {
                            tvDaily.setText("연유");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림, 버터");
                        }

                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if(dayOfMonth == 7)
                        {
                            tvDaily.setText("피스타치오");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 해바라기, 호두, 땅콩");
                        }
                        else if(dayOfMonth == 16)
                        {
                            tvDaily.setText("해바라기");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 호두, 땅콩");
                        }
                        else
                        {
                            tvDaily.setText("호두 or 땅콩");
                            tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기");
                        }
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("토마토");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토란, 부추");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("토란");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 부추");
                        }
                        else
                        {
                            tvDaily.setText("부추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 토란");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            tvDaily.setText("망고");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else if(dayOfMonth == 18)
                        {
                            tvDaily.setText("레몬 or 오렌지");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else
                        {
                            tvDaily.setText("딸기 or 키위");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                    }
                }
                else if(babymonth == 15 || babymonth == 16 || babymonth == 17)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        tvDaily.setText("율무");
                        tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두, 흑미, 밀가루, 팥");
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("소고기 양지머리");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 삼겹살");
                        }
                        else
                        {
                            tvDaily.setText("삼겹살");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 소고기 양지머리");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        tvDaily.setText("굴비");
                        tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 고등어, 모시조개, 맛조개, 홍합, 바지락, 소라, 전복");
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        tvDaily.setText("한천");
                        tvChange.setText("파래, 김, 다시마,미역");
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 흰자");
                        tvChange.setText("달걀 노른자, 메추리 알");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            tvDaily.setText("생우유");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림, 버터, 연유");
                        }
                        else
                        {
                            tvDaily.setText("연유");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림, 버터, 생우유");
                        }

                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        tvDaily.setText("호박씨");
                        tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기, 호두, 땅콩");
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("토마토");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토란, 부추");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("토란");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 부추");
                        }
                        else
                        {
                            tvDaily.setText("부추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 토란");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            tvDaily.setText("망고");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else if(dayOfMonth == 18)
                        {
                            tvDaily.setText("레몬 or 오렌지");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else
                        {
                            tvDaily.setText("딸기 or 키위");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                    }
                }
                else if(babymonth == 18 || babymonth == 19 || babymonth == 20 || babymonth == 21 || babymonth ==22 || babymonth ==23)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        tvDaily.setText("율무");
                        tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두, 흑미, 밀가루, 팥");
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("소고기 양지머리");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 삼겹살");
                        }
                        else
                        {
                            tvDaily.setText("삼겹살");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 소고기 양지머리");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        tvDaily.setText("굴비");
                        tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 고등어, 모시조개, 맛조개, 홍합, 바지락, 소라, 전복");
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        tvDaily.setText("한천");
                        tvChange.setText("파래, 김, 다시마,미역");
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 흰자");
                        tvChange.setText("달걀 노른자, 메추리 알");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                            tvDaily.setText("모차렐라 치즈");
                            tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림, 버터, 생우유, 연우");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        tvDaily.setText("호박씨");
                        tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기, 호두, 땅콩");
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("토마토");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토란, 부추");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("토란");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 부추");
                        }
                        else
                        {
                            tvDaily.setText("부추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 토란");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            tvDaily.setText("망고");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else if(dayOfMonth == 18)
                        {
                            tvDaily.setText("레몬 or 오렌지");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else
                        {
                            tvDaily.setText("딸기 or 키위");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                    }
                }
                else if(babymonth >= 24)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        tvDaily.setText("혼합 잡곡");
                        tvChange.setText("쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두, 흑미, 밀가루, 팥, 율무");
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            tvDaily.setText("소고기 양지머리");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 삼겹살");
                        }
                        else
                        {
                            tvDaily.setText("삼겹살");
                            tvChange.setText("닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지고기 등심, 닭 날개, 닭 다리, 소고기 양지머리");
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        tvDaily.setText("건새우");
                        tvChange.setText("가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴, 날치알, 오징어, 게, 고등어, 모시조개, 맛조개, 홍합, 바지락, 소라, 전복, 굴비");
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        tvDaily.setText("한천");
                        tvChange.setText("파래, 김, 다시마,미역");
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        tvDaily.setText("달걀 흰자");
                        tvChange.setText("달걀 노른자, 메추리 알");
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        tvDaily.setText("모차렐라 치즈");
                        tvChange.setText("플레인 요구르트, 마가린, 아기용 치즈, 액상 요구르트, 생크림, 버터, 생우유, 연우");
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        tvDaily.setText("아몬드");
                        tvChange.setText("참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기, 호두, 땅콩, 호박씨");
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            tvDaily.setText("토마토");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토란, 부추");
                        }
                        else if(dayOfMonth == 17)
                        {
                            tvDaily.setText("토란");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 부추");
                        }
                        else
                        {
                            tvDaily.setText("부추");
                            tvChange.setText("감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 콩나물, 숙주, 치커리, 무순 ,가지, 파프리카, 쑥, 냉이, 고사리, 깻잎, 토마토, 토란");
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            tvDaily.setText("망고");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else if(dayOfMonth == 18)
                        {
                            tvDaily.setText("레몬 or 오렌지");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                        else
                        {
                            tvDaily.setText("딸기 or 키위");
                            tvChange.setText("사과, 배, 바나나, 자두, 수박, 귤, 참외, 멜론, 건포도, 포도, 살구, 파인애플, 복숭아, 단감");
                        }
                    }
                }
                else
                {
                    tvDaily.setText("아직 태어나지 않았어요.");
                    tvChange.setText("아직 태어나지 않았어요.");
                }
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