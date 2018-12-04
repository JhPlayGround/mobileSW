package com.example.helloworld.myapplication.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.util.LoginActivity;

public class post extends Activity {

    Context mContext;

    String  stitle, sbody;
    EditText edTitle, edBody;
    Button send, cancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_main);
        mContext = this.getBaseContext();
        edTitle = (EditText)findViewById(R.id.title_post);
        edBody = (EditText)findViewById(R.id.body_post);
        stitle = edTitle.getText().toString();
        sbody = edBody.getText().toString();

        send = (Button)findViewById(R.id.send_Btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BoardFragment.title = edTitle.getText().toString();
                BoardFragment.body = edBody.getText().toString();

                BoardFragment.re();

                finish();
            }
        });

        cancel = (Button)findViewById(R.id.cancel_Btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
