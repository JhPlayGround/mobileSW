package com.example.helloworld.myapplication.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;

public class BoardFragment extends Fragment {
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
                Intent get = new Intent(getContext(), get.class);
                get.putExtra("title",title);
                get.putExtra("body",body);
                startActivity(get);
            }
        });
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);



        if(mMyAdapter == null)
        {
            mMyAdapter = new MyAdapter();
        }else{
            if(title.equals("")){
                mMyAdapter.notifyDataSetChanged();
            }else if(body.equals("")){
                mMyAdapter.notifyDataSetChanged();
            }else if(body.equals("") && title.equals(""))
                dataSetting(mMyAdapter,title,body);
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent post = new Intent(getContext(), post.class);
                startActivity(post);
            }
        });
        return view;
    }

    public void dataSetting(MyAdapter mMyAdapter,String title, String body){
        mMyAdapter.addItem(title, body);
        mMyAdapter.notifyDataSetChanged();
        mListView.setAdapter(mMyAdapter);
    }

    public static void re(){
        mMyAdapter.addItem(title, body);
        mMyAdapter.notifyDataSetChanged();
        mListView.setAdapter(mMyAdapter);
    }

}
