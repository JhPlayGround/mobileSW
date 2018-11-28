package com.example.helloworld.myapplication.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.weather.ForeCastManager;
import com.example.helloworld.myapplication.weather.WeatherInfo;
import com.example.helloworld.myapplication.weather.WeatherToHangeul;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ClothesFragment extends Fragment {
    public static final int THREAD_HANDLER_SUCCESS_INFO = 1;
    MainActivity activity;
    ForeCastManager mForeCast;

    TextView tvLocal;
    TextView tvTemp;
    TextView tvCloud;
    ImageView ivCloud;
    TextView tvClothesData;
    TextView tvDustData;
    Button btnSetGPS;

    //날짜 변수
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("dd HH");
    String nTime;

    //설정할 GPS
    String Slat;
    String Slon;
    //자신의 위치 받기
    String city;

    //위치정보를 공급하는 근원
    String locationProvider;
    //위치 정보 매니져 객체
    LocationManager locationManager;

    //기본 GPS설정
    String lon = "126"; // 경도 설정
    String lat = "37";  // 위도 설정
    ArrayList<ContentValues> mWeatherData;
    ArrayList<WeatherInfo> mWeatherInfomation;
    ClothesFragment mThis;

    @Override
    public void onAttach(Context context) {
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.main_clothes, mainFragmentLayout, false);

        //지역
        tvLocal = (TextView) view.findViewById(R.id.tvLocal);
        //온도
        tvTemp = (TextView) view.findViewById(R.id.tvTemp);
        //구름량
        tvCloud = (TextView) view.findViewById(R.id.tvCloud);
        //구름 이미지
        ivCloud = (ImageView) view.findViewById(R.id.ivCloud);
        //미세먼지
        tvDustData = (TextView) view.findViewById(R.id.tvDustData);

        tvClothesData = (TextView) view.findViewById(R.id.tvClothesData);

        btnSetGPS = (Button) view.findViewById(R.id.btnSetGPS);
        Button btnLogout = (Button) view.findViewById(R.id.btnLogout);
        Button btnInformation = (Button) view.findViewById(R.id.btnInformation);

        // LocationManager 객체를 얻어온다
        final LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //자신의 위치 GPS 값 받기
        btnSetGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "위치정보 수신중 . . .", Toast.LENGTH_SHORT).show();
                // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                        1000, // 통지사이의 최소 시간간격 (miliSecond)
                        0, // 통지사이의 최소 변경거리 (m)
                        mLocationListener);
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                        1000, // 통지사이의 최소 시간간격 (miliSecond)
                        0, // 통지사이의 최소 변경거리 (m)
                        mLocationListener);

                //위치정보 받을 대기시간
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable()  {
                    public void run() {

                        Toast.makeText(getContext(),"위치 정보를 갱신하였습니다.",Toast.LENGTH_SHORT).show();

                        lon = Slon;
                        lat = Slat;

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(mThis).attach(mThis).commit();

                        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.

                    }
                }, 15000);


            }
        });

        //로그아웃 버튼
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.LOGINRECORD == 1)
                {
                    Toast.makeText(getActivity(), "로그이아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    MainActivity.LOGINRECORD = 0;
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        //앱 정보 버튼
        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_information, (ViewGroup) v.findViewById(R.id.popup));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(getContext());

                aDialog.setTitle("개월 수 별 먹어도 되는 음식들");
                aDialog.setView(layout);

                AlertDialog ad = aDialog.create();
                ad.show();
            }
        });

        Initialize();
        getTime();

        return view;
    }

    //초기화
    public void Initialize() {

        mWeatherInfomation = new ArrayList<>();
        mThis = this;
        mForeCast = new ForeCastManager(lon, lat, mThis);
        mForeCast.run();
    }
    //현재 시간 메소드
    public void getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        nTime = mFormat.format(mDate);

        tvDustData.setText(nTime);
      //  if(nTime.equals(mWeatherInfomation.get(1).getWeather_Day_Go()))
       // for(int i=0; i < mWeatherInfomation.size(); i++)
        //{
        //}
    }
    //지역 출력 메소드
    public String LocalPrint(){
        String LocalData = "";

        Bundle bundle = getArguments();
        if(bundle != null )
        {
            city = bundle.getString("city");
            LocalData = city;
        }

        return LocalData;
    }
    /* 1 : 00 ~ 03 / 2 : 03 ~ 06 / 3 : 06 ~ 09 / 4 : 09 ~ 12
       5 : 12 ~ 15 / 6 : 15 ~ 18 / 7 : 18 ~ 21 / 8 : 21 ~ 24 */
    //온도 출력 메소드
    public String TempPrint(){
        String TempData = "";
        TempData =  "최대 기온 : " + mWeatherInfomation.get(1).getTemp_Max() + "\n"+
                    "최저 기온 : " + mWeatherInfomation.get(1).getTemp_Min();
        return TempData;
    }
    //구름량 출력 메소드
    public String CloudPrint(){
        String CloudData = "";
        CloudData =  mWeatherInfomation.get(1).getClouds_Value() + "%";
        return CloudData;
    }

    public String PrintValue() {
        String mData = "";
        for (int i = 0; i < mWeatherInfomation.size(); i++) {
            mData = mData + mWeatherInfomation.get(i).getWeather_Day_Go() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Day_End() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Name() + "\r\n"
                    + mWeatherInfomation.get(i).getClouds_Sort() + "\r\n"
                    + "구름 양 : " + mWeatherInfomation.get(i).getClouds_Value()
                    + mWeatherInfomation.get(i).getClouds_Per() + "\r\n"
                    + mWeatherInfomation.get(i).getWind_Name() + "\r\n"
                    + "바람 속도 : " + mWeatherInfomation.get(i).getWind_Speed() + " mps" + "\r\n"
                    + "최대 기온 : " + mWeatherInfomation.get(i).getTemp_Max() + "℃" + "\r\n"
                    + "최저 기온: " + mWeatherInfomation.get(i).getTemp_Min() + "℃" + "\r\n"
                    + "습도: " + mWeatherInfomation.get(i).getHumidity() + "%" + "\r\n"
                    + "지역: " + city;
            ;
                    //+ "i의 크기 : " + mWeatherInfomation.get(i).getCity();

            mData = mData + "\r\n" + "----------------------------------------------" + "\r\n";
        }
        return mData;
    }

    public void DataChangedToHangeul() {
        for (int i = 0; i < mWeatherInfomation.size(); i++) {
            WeatherToHangeul mHangeul = new WeatherToHangeul(mWeatherInfomation.get(i));
            mWeatherInfomation.set(i, mHangeul.getHangeulWeather());
        }
    }


    public void DataToInformation() {
        for (int i = 0; i < mWeatherData.size(); i++) {
            mWeatherInfomation.add(new WeatherInfo(
                    String.valueOf(mWeatherData.get(i).get("weather_Name")), String.valueOf(mWeatherData.get(i).get("weather_Number")), String.valueOf(mWeatherData.get(i).get("weather_Much")),
                    String.valueOf(mWeatherData.get(i).get("weather_Type")), String.valueOf(mWeatherData.get(i).get("wind_Direction")), String.valueOf(mWeatherData.get(i).get("wind_SortNumber")),
                    String.valueOf(mWeatherData.get(i).get("wind_SortCode")), String.valueOf(mWeatherData.get(i).get("wind_Speed")), String.valueOf(mWeatherData.get(i).get("wind_Name")),
                    String.valueOf(mWeatherData.get(i).get("temp_Min")), String.valueOf(mWeatherData.get(i).get("temp_Max")), String.valueOf(mWeatherData.get(i).get("humidity")),
                    String.valueOf(mWeatherData.get(i).get("Clouds_Value")), String.valueOf(mWeatherData.get(i).get("Clouds_Sort")), String.valueOf(mWeatherData.get(i).get("Clouds_Per")),
                    String.valueOf(mWeatherData.get(i).get("weather_Day_Go")), String.valueOf(mWeatherData.get(i).get("weather_Day_End")), String.valueOf(mWeatherData.get(i).get("city"))
            ));

        }

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case THREAD_HANDLER_SUCCESS_INFO:
                    mForeCast.getmWeather();
                    mWeatherData = mForeCast.getmWeather();
                    if (mWeatherData.size() == 0)
                        tvLocal.setText("데이터가 없습니다");
                       tvTemp.setText("데이터가 없습니다");
                       tvCloud.setText("데이터가 없습니다");

                    DataToInformation(); // 자료 클래스로 저장,

                    String localData = "";
                    String tempData = "";
                    String cloudData = "";
                    String print="";

                    DataChangedToHangeul();

                    localData = localData + LocalPrint();
                    tempData = tempData + TempPrint();
                    cloudData = cloudData + CloudPrint();
                    print = print + PrintValue();

                    tvLocal.setText(localData);
                    tvTemp.setText(tempData);
                    tvCloud.setText(cloudData);
                    tvClothesData.setText(print);

                    break;
                default:
                    break;
            }
        }
    };

    //GPS 값 전달
    private LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            double updateLon = location.getLongitude(); //경도
            double updateLat = location.getLatitude();   //위도

            Slat = String.valueOf(updateLat);
            Slon = String.valueOf(updateLon);

        }

        public void onProviderDisabled(String provider) {
            checkProvider(provider);
        }

        public void onProviderEnabled(String provider) {
            alterProvider(provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            alterStatus(provider);
        }
    };

    public void checkProvider(String provider){
        Toast.makeText(getContext(),provider + "를 켜주세요.",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    public void alterProvider(String provider)
    {
        Toast.makeText(getContext(),provider + "가 켜졌습니다.",Toast.LENGTH_SHORT).show();
    }

    public void alterStatus(String provider)
    {
        Toast.makeText(getContext(),"위치서비스가 "+provider+"로 변경되었습니다.",Toast.LENGTH_SHORT).show();
    }

}