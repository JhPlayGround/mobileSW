package com.example.helloworld.myapplication.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.ToggleButton;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.weather.ForeCastManager;
import com.example.helloworld.myapplication.weather.WeatherInfo;
import com.example.helloworld.myapplication.weather.WeatherToHangeul;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClothesFragment extends Fragment {
    public static final int THREAD_HANDLER_SUCCESS_INFO = 1;
    MainActivity activity;
    ForeCastManager mForeCast;
    TextView tvLocal;
    TextView tvTemp;
    TextView tvCloud;
    ImageView ivCloud;
    TextView tvClothesData;
    String city;
    TextView tvDustData;
    ToggleButton btnSetGPS;

    //날짜 변수
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("dd hh");
    String nTime;

    String Slat;
    String Slon;

    //위치정보를 공급하는 근원
    String locationProvider;
    //위치 정보 매니져 객체
    LocationManager locationManager;

    String longitude;
    String latitude;
    String lon = "96"; // 경도 설정
    String lat = "32";  // 위도 설정
    ArrayList<ContentValues> mWeatherData;
    ArrayList<WeatherInfo> mWeatherInfomation;
    ClothesFragment mThis;

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
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.main_clothes,mainFragmentLayout,false);

        //지역
        tvLocal = (TextView) view.findViewById(R.id.tvLocal);
        //온도
        tvTemp = (TextView)view.findViewById(R.id.tvTemp);
        //구름량
        tvCloud = (TextView)view.findViewById(R.id.tvCloud);
        //구름 이미지
        ivCloud = (ImageView)view.findViewById(R.id.ivCloud);
        //미세먼지
        tvDustData = (TextView)view.findViewById(R.id.tvDustData);

        tvClothesData = (TextView)view.findViewById(R.id.tvClothesData);

        final ToggleButton btnSetGPS = (ToggleButton) view.findViewById(R.id.btnSetGPS);
        Button btnrefresh = (Button)view.findViewById(R.id.btnrefresh);
        //SetGPS 이동


                // LocationManager 객체를 얻어온다
                final LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                btnSetGPS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if(btnSetGPS.isChecked()){
                                Toast.makeText(getContext(),"위치정보 수신중 . . .",Toast.LENGTH_SHORT).show();
                                // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                        1000, // 통지사이의 최소 시간간격 (miliSecond)
                                        0, // 통지사이의 최소 변경거리 (m)
                                        mLocationListener);
                                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                        1000, // 통지사이의 최소 시간간격 (miliSecond)
                                        0, // 통지사이의 최소 변경거리 (m)
                                        mLocationListener);

                            }else{
                                Toast.makeText(getContext(),"위치정보 미수신",Toast.LENGTH_SHORT).show();
                                lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
                            }
                        }catch(SecurityException ex){
                        }
                    }
                });

        btnrefresh.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View v) {
        lon = Slon;
        lat = Slat;

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(mThis).attach(mThis).commit();

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

    // Location 제공자에서 정보를 얻어오기(GPS)
    // 1. Location을 사용하기 위한 권한을 얻어와야한다 AndroidManifest.xml
    //     ACCESS_FINE_LOCATION : NETWORK_PROVIDER, GPS_PROVIDER
    //     ACCESS_COARSE_LOCATION : NETWORK_PROVIDER
    // 2. LocationManager 를 통해서 원하는 제공자의 리스너 등록
    // 3. GPS 는 에뮬레이터에서는 기본적으로 동작하지 않는다
    // 4. 실내에서는 GPS_PROVIDER 를 요청해도 응답이 없다.  특별한 처리를 안하면 아무리 시간이 지나도
    //    응답이 없다.
    //    해결방법은
    //     ① 타이머를 설정하여 GPS_PROVIDER 에서 일정시간 응답이 없는 경우 NETWORK_PROVIDER로 전환
    //     ② 혹은, 둘다 한꺼번헤 호출하여 들어오는 값을 사용하는 방식.

    private LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            double lon = location.getLongitude(); //경도
            double lat = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자

            Toast.makeText(getContext(),"위치 정보를 받았습니다.",Toast.LENGTH_SHORT);

            Slat = String.valueOf(lat);
            Slon = String.valueOf(lon);

            //tv.setText("위치정보 : " + provider + "\n위도 : " + Slon + "\n경도 : " + Slat
              //      + "\n고도 : " + altitude + "\n정확도 : "  + accuracy);

            //Fragment fragment = new ClothesFragment();
            //Bundle gps = new Bundle();
            //gps.putString("lon",Slon);
            //gps.putString("lat",Slat);
            //ClothesFragment에 접근하기 위해 ClothesFragment mContext 사용
            //fragment.setArguments(gps);



        }

        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };

}