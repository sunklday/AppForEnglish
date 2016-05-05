package com.jiai.sun.appforenglist.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jiai.sun.appforenglist.Adapter.RecordRecyclerViewAdapter;
import com.jiai.sun.appforenglist.DB.RecordDBManager;
import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Record;
import com.jiai.sun.appforenglist.view.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class LearningRecordActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<RecordRecyclerViewAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_record);
        mRecyclerView = (RecyclerView) findViewById(R.id.ryv_recordActivity);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        InitRecordRecyclerView initRecordRecyclerView = new InitRecordRecyclerView();
        initRecordRecyclerView.execute();
    }

    class InitRecordRecyclerView extends AsyncTask<Void,Void,List<Record>>{

        @Override
        protected List<Record> doInBackground(Void... params) {
            return initRecordData();
        }

        @Override
        protected void onPostExecute(List<Record> recordList) {
             mAdapter = new RecordRecyclerViewAdapter(recordList);

            mRecyclerView.setAdapter(mAdapter);

        }
    }

    private List<Record> initRecordData(){
        RecordDBManager recordDBManager = new RecordDBManager(getApplicationContext());
        return recordDBManager.findRecordById(1);
    }
}
