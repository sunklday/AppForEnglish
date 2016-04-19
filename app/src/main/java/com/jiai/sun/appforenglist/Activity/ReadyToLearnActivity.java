package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jiai.sun.appforenglist.Adapter.ReadyToLearnRecyclerViewAdapter;
import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.ArrayList;
import java.util.List;

public class ReadyToLearnActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<ReadyToLearnRecyclerViewAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Words> wordsList;
    private Integer amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_to_learn);
        mRecyclerView = (RecyclerView) findViewById(R.id.ryv_readToLearnRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        InitRecyclerView initRecyclerView = new InitRecyclerView();
        initRecyclerView.execute();


    }


    class InitRecyclerView extends AsyncTask<Void,Void,List<Words>>{

        @Override
        protected List<Words> doInBackground(Void... params) {

            return initData();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Words> wordses) {
            mAdapter = new ReadyToLearnRecyclerViewAdapter(wordses);
            mRecyclerView.setAdapter(mAdapter);
        }


    }

    private List<Words> initData(){
        UserDBManager userDBManager = new UserDBManager(getApplicationContext());
        amount = userDBManager.findUserById(1).getAmount();
        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        wordsList= wordsDBManager.getWorsByAmount(amount);
        return wordsList;
    }


    /**
     * button onclick事件，启动learnEnglishActivity
     * @param view
     */
    public void startLearnEnglishActivity(View view){
        ArrayList<Integer> idArrayList = new ArrayList<>();
        for (int i =0;i<amount;i++){
            idArrayList.add(Integer.valueOf(wordsList.get(i).get_id()));
        }
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("wordsIdArrayList", idArrayList);
        Intent intent = new Intent(getApplicationContext(),LearnEnglishActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
