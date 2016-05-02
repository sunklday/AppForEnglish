package com.jiai.sun.appforenglist.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jiai.sun.appforenglist.DB.WordsDBManager;
import com.jiai.sun.appforenglist.R;
import com.jiai.sun.appforenglist.domain.Words;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private boolean correct;
    private TextView txvTitle;
    private   List<Words> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recview);
        toolbar = (Toolbar) findViewById(R.id.toolbar_review);
        btnA = (Button) findViewById(R.id.btn_A);
        btnB = (Button) findViewById(R.id.btn_B);
        btnC = (Button) findViewById(R.id.btn_C);
        btnD = (Button) findViewById(R.id.btn_D);
        txvTitle = (TextView) findViewById(R.id.txv_review_title);
        toolbar.setTitle("测试");
        setSupportActionBar(toolbar);
        initDate();
    }

    private void initDate(){
        WordsDBManager wordsDBManager =new WordsDBManager(getApplicationContext());

        list  = wordsDBManager.findWordbyReview("10");

    }


    private String getMean(String word){
        for(Words words:list){
            if (words.getWord().equals(word)){
                return words.getMean();
            }
        }
        return null;
    }

    public void checking(View view){
        Button button = (Button) view;
        String bMean = button.getText().toString();
        String trueMean = getMean(this.txvTitle.getText().toString());
       if(bMean.equals(trueMean)){
           button.setText("12313");
           button.setBackgroundColor(getResources().getColor(R.color.correct_true));
           this.correct =true;
       }else {
           button.setBackgroundColor(getResources().getColor(R.color.correct_false));
           this.correct=false;
       }
    }


}
