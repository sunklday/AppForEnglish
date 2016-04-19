package com.jiai.sun.appforenglist.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.jiai.sun.appforenglist.R;

public class LearningRecordActivity extends AppCompatActivity {

    private RecyclerView mrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_record);
        mrecyclerView = (RecyclerView) findViewById(R.id.ryv_recordActivity);

    }
}
