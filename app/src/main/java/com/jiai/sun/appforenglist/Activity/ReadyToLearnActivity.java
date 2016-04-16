package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiai.sun.Adapter.ReadyToLearnRecyclerViewAdapter;
import com.jiai.sun.appforenglist.R;

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new ReadyToLearnRecyclerViewAdapter(setData());
        mRecyclerView.setAdapter(mAdapter);
    }
    private List<String> setData(){
        List<String> data = new ArrayList<String>();
        for(int i = 0;i<20;i++){
            data.add("word:" + i);
        }
        return data;
    }
    public void startLearnEnglishActivity(View view){
        Intent intent = new Intent(getApplicationContext(),LearnEnglishActivity.class);
        startActivity(intent);
    }
}
