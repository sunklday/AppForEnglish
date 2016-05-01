package com.jiai.sun.appforenglist.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recview);
        toolbar = (Toolbar) findViewById(R.id.toolbar_review);
    /*    btnA = (Button) findViewById(R.id.btn_A);
        btnB = (Button) findViewById(R.id.btn_B);
        btnC = (Button) findViewById(R.id.btn_C);
        btnD = (Button) findViewById(R.id.btn_D);*/
        txvTitle = (TextView) findViewById(R.id.txv_review_title);
        toolbar.setTitle("测试");
        setSupportActionBar(toolbar);
     /*   List<Words> list  = new ArrayList<>();
        for(int i=0; i<4;i++){
            Words words = new Words();
            words.setWord("word"+i);
            words.setMean("mean"+i);
            list.add(words);
        }*/

    }
    private String getMean(String word){
        return null;
    }



}
