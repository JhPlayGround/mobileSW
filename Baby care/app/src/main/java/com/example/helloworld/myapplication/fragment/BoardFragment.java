package com.example.helloworld.myapplication.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class BoardFragment extends Fragment {

    MainActivity activity;
    Button addButton;

    int i;
    MyAdapter mMyAdapter;
    private ListView mListView;

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
        mMyAdapter = new MyAdapter();
        i = 0;
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //dataSetting(mMyAdapter,i);
                //i++;
                Intent post = new Intent(getContext(), post.class);
                startActivity(post);
            }

        });
        return view;
    }
    /*
    public void dataSetting(MyAdapter mMyAdapter,int i){
        mMyAdapter.addItem("name_" + i, "contents_" + i);
        mMyAdapter.notifyDataSetChanged();
        mListView.setAdapter(mMyAdapter);
    }*/
}
