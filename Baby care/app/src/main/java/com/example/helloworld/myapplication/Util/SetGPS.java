package com.example.helloworld.myapplication.util;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.fragment.ClothesFragment;

public class SetGPS extends AppCompatActivity {

    ClothesFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_setgps);


    }
}
