package com.babycare.helloworld.myapplication.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.babycare.helloworld.myapplication.R;

public class get extends Activity {

    Context mContext;

    TextView tvTitle, tvBody;

    Button back;

    public static String title;
    public static String body;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_main);
        mContext = this.getBaseContext();
        tvTitle = (TextView)findViewById(R.id.title_get);
        tvBody = (TextView)findViewById(R.id.body_get);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                title= null;
                body = null;
            } else {
                title= extras.getString("title");
                body= extras.getString("body");
            }
        } else {
            title= (String) savedInstanceState.getSerializable("title");
            body = (String) savedInstanceState.getSerializable("body");
        }

        tvTitle.setText(title);
        tvBody.setText(body);

        back = (Button)findViewById(R.id.back_Btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
