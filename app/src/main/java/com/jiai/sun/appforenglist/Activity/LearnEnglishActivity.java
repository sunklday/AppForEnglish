package com.jiai.sun.appforenglist.Activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jiai.sun.appforenglist.Adapter.CardStackAdapter;
import com.jiai.sun.appforenglist.DB.UserDBManager;
import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;
import com.wenchao.cardstack.CardStack;

import java.util.ArrayList;
import java.util.List;

public class LearnEnglishActivity extends AppCompatActivity {
    private Button button;

    private CardStack mCardStack;
    private CardStackAdapter mCardAdapter;;
    private ArrayList<Integer> wordsIdArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_english);
        mCardStack = (CardStack)findViewById(R.id.csk_englishCard);
        mCardStack.setContentResource(R.layout.learn_english_card_content);
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
            mCardStack.setStackMargin(20);
            mCardAdapter = new CardStackAdapter(getApplicationContext(),wordsList);
            for (int i=1;i<=wordsIdArrayList.size();i++){
                mCardAdapter.add(i+"");
            }
            LearnEnglishCardStackListener myListener = new LearnEnglishCardStackListener(getApplicationContext());
            mCardStack.setListener(myListener);
            mCardStack.setAdapter(mCardAdapter);
        }
    }
    private List<Words> initData(){

        Bundle bundle = getIntent().getExtras();
        wordsIdArrayList = (ArrayList<Integer>) bundle.get("wordsIdArrayList");
        WordsDBManager wordsDBManager = new WordsDBManager(getApplicationContext());
        List<Words> wordsList = new ArrayList<>();
        for(Integer i:wordsIdArrayList){
            wordsList.add(wordsDBManager.getWorsById(i));
        }

        return wordsList;
    }
}
