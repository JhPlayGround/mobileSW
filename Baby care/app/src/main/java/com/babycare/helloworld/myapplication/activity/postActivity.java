package com.babycare.helloworld.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.babycare.helloworld.myapplication.R;
import com.babycare.helloworld.myapplication.fragment.BoardFragment;
import com.babycare.helloworld.myapplication.util.Login;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class postActivity extends Activity {

    public static String Id;

    Context mContext;

    String stitle, sbody;
    EditText edTitle, edBody;
    Button send, cancel;

    text task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_main);
        mContext = this.getBaseContext();
        edTitle = (EditText) findViewById(R.id.title_post);
        edBody = (EditText) findViewById(R.id.body_post);
        stitle = edTitle.getText().toString();
        sbody = edBody.getText().toString();

        send = (Button) findViewById(R.id.send_Btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = edTitle.getText().toString();
                String body = edBody.getText().toString();

                task = new text();
                task.execute(title,body);

                BoardFragment.re();

                finish();
            }
        });

        cancel = (Button) findViewById(R.id.cancel_Btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private class text extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String title = arg0[0];
                String text = arg0[1];

                String link = "http://otl9882.codns.com:443/board.php?Title=" + title +"&Text=" + text;

                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }


    }
}
