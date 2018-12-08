package com.babycare.helloworld.myapplication.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.babycare.helloworld.myapplication.R;
import com.babycare.helloworld.myapplication.activity.MainActivity;
import com.babycare.helloworld.myapplication.activity.postActivity;
import com.babycare.helloworld.myapplication.util.MyAdapter;
import com.babycare.helloworld.myapplication.util.MyItem;
import com.babycare.helloworld.myapplication.util.get;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class BoardFragment extends Fragment {
    private static board task;
    MainActivity activity;

    Button addButton;

    public static MyAdapter mMyAdapter;
    public static ListView mListView;

    public static String title;
    public static String body;


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

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.main_board,mainFragmentLayout,false);
        addButton = (Button)view.findViewById(R.id.add) ;

        mListView = (ListView)view.findViewById(R.id.listView);
        mListView.setAdapter(mMyAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyItem myItem = (MyItem) parent.getItemAtPosition(position);

                Bundle extras = new Bundle();
                extras.putString("title",myItem.getName());
                extras.putString("body",myItem.getContents());
                Intent get = new Intent(getContext(), get.class);
                get.putExtras(extras);
                startActivity(get);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post = new Intent(getContext(), postActivity.class);
                startActivity(post);
            }
        });

        re();

        return view;
    }

    public static void re(){

        mMyAdapter = new MyAdapter();
        task = new board();
        task.execute();
    }

    private static class board extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            try {

                String link = "http://otl9882.codns.com:443/list.php";

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

        @Override
        protected void onPostExecute(String result) {

                for (int i = 0; i <result.length(); i++) {
                    try {
                        if (i == 0)
                        {
                            title = result.split(",")[0];
                            body = result.split(",")[1];
                            mMyAdapter.addItem(title, body);
                            mMyAdapter.notifyDataSetChanged();
                            mListView.setAdapter(mMyAdapter);
                        }
                        else if (!result.split(",")[i].isEmpty()) {
                            int a = i*2;
                            title = result.split(",")[i*2];
                            body = result.split(",")[a+1];
                            mMyAdapter.addItem(title, body);
                            mMyAdapter.notifyDataSetChanged();
                            mListView.setAdapter(mMyAdapter);
                        }
                        else {

                        }
                    }
                    catch (Exception e)
                    {
                        System.out.print(e);
                    }
            }
                title = "";
                body = "";
        }
    }
}
