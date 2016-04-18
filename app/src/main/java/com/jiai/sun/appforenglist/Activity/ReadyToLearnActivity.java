package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiai.sun.appforenglist.Adapter.ReadyToLearnRecyclerViewAdapter;
import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.User;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.ArrayList;
import java.util.List;

public class ReadyToLearnActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<ReadyToLearnRecyclerViewAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_learn);
        mRecyclerView = (RecyclerView) findViewById(R.id.ryv_readToLearnRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ReadyToLearnRecyclerViewAdapter(setData());
        mRecyclerView.setAdapter(mAdapter);
    }
    private List<Words> setData(){
        UserDBManager userDBManager = new UserDBManager(getApplicationContext());
        Integer amount = userDBManager.findUserById(1).getAmount();

        List<Words> data = new ArrayList<Words>();

        return data;
    }
    public void startLearnEnglishActivity(View view){
        Intent intent = new Intent(getApplicationContext(),LearnEnglishActivity.class);
        startActivity(intent);
    }
}
