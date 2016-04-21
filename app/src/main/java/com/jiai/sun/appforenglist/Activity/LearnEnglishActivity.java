package com.jiai.sun.appforenglist.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiai.sun.appforenglist.Adapter.CardStackAdapter;
import com.jiai.sun.appforenglist.DB.DBOpenHelper;
import com.jiai.sun.appforenglist.DB.RecordDBManager;
import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Record;
import com.jiai.sun.appforenglist.domain.Words;
import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LearnEnglishActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private CardStack mCardStack;
    private CardStackAdapter mCardAdapter ;
    private ArrayList<Integer> wordsIdArrayList;
    private List<Words> wordsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_english);
        mCardStack = (CardStack)findViewById(R.id.csk_englishCard);
        button = (Button) findViewById(R.id.btn_learnEnglishActivityComeBack);
        textView = (TextView) findViewById(R.id.txv_learnEnglishActivityComeBack);
        mCardStack.setContentResource(R.layout.learn_english_card_content);
        mCardStack.setStackMargin(20);

        Bundle bundle = getIntent().getExtras();
        wordsIdArrayList = (ArrayList<Integer>) bundle.get("wordsIdArrayList");

        InitCardStack initCardStack = new InitCardStack();
        initCardStack.execute();

    }

    class InitCardStack extends AsyncTask<Void,Void,List<Words>>{

        @Override
        protected List<Words> doInBackground(Void... params) {
            return initData();
        }

        @Override
        protected void onPostExecute(List<Words> wordsList) {

            mCardAdapter = new CardStackAdapter(getApplicationContext(),wordsList);
            for (int i=1;i<=wordsIdArrayList.size();i++){
                mCardAdapter.add(i+"");
            }
            LearnEnglishCardStackListener myListener = new LearnEnglishCardStackListener(getApplicationContext());
            mCardStack.setListener(myListener);
            mCardStack.setAdapter(mCardAdapter);
            textView.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }
    }
    private List<Words> initData(){


        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        wordsList = new ArrayList<>();
        for(Integer i:wordsIdArrayList){
            wordsList.add(wordsDBManager.getWorsById(i));
        }
        return wordsList;
    }

    /**
     * 点击button返回时候更新word表添加degree，更新record添加学习记录
     * @param view
     */
    public void comeBack(View view){
        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        for (Words word:wordsList) {
            word.setDegree("1");
            wordsDBManager.updateWord(word);
        }
        Record record  = new Record();
        record.setWordsCount(wordsList.size());
        record.setUserId(1);
        record.setDifficultWordsCount(0);
        record.setStarWordsCount(0);
        record.setEasyWordsCount(0);
        record.setLearnDate(String.valueOf(new Date()));
        RecordDBManager recordDBManager = new RecordDBManager(getApplicationContext());
        recordDBManager.addRecord(record);
        /**
         * 将来添加星标单词
         *
         */
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
}
